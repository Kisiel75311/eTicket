package com.electronicTicket.services;

import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.models.Account;
import com.electronicTicket.models.enums.Role;
import com.electronicTicket.models.Ticket;
import com.electronicTicket.models.TicketType;
import com.electronicTicket.models.enums.TicketTypeEnum;
import com.electronicTicket.repositories.AccountRepository;
import com.electronicTicket.repositories.TicketRepository;
import com.electronicTicket.repositories.TicketTypeRepository;
import com.electronicTicket.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AccountRepository accountRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TransactionService transactionService;
    private final ModelMapper modelMapper;


    public TicketDto buyTicket(Long accountId, Long ticketTypeId, Long vehicleId) {
        // Sprawdzamy czy istnieje konto z danym ID
        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new NoSuchElementException("Account with id " + accountId + " not found."));
        // Sprawdzamy czy konto ma role pasażera
        if (account.getRole() != Role.ROLE_PASSENGER) {
            throw new IllegalArgumentException("Only accounts with role PASSENGER can buy tickets.");
        }
        // Sprawdzamy czy istnieje typ biletu z danym ID
        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                .orElseThrow(() -> new NoSuchElementException("Ticket type with id " + ticketTypeId + " not found."));
        // Sprawdzamy czy konto ma wystarczającą ilość środków do zakupu biletu
        if (account.getBalance().compareTo(ticketType.getPrice()) < 0) {
            throw new IllegalArgumentException("Account does not have sufficient funds to buy this ticket.");
        }

        // Tworzymy nowy bilet
        Ticket ticket = new Ticket();
        ticket.setAccount(account);
        ticket.setTicketType(ticketType);
        ticket.setPurchaseDate(new Date());
        // ustalamy date ważności biletu w zależności od typu biletu, tutaj przykładowo dodajemy 1 godzine do czasu zakupu
        ticket.setExpirationDate(calculateExpirationDate(ticketType));
        // Aktywujemy bilet
        ticket.activate(vehicleId);

        // Zapisujemy bilet do bazy danych
        ticketRepository.save(ticket);

        // Tworzymy nową transakcje
        transactionService.registerTransaction(accountId, ticket.getTicketCode());

        // Aktualizujemy saldo konta
        account.setBalance(account.getBalance().subtract(ticketType.getPrice()));
        accountRepository.save(account);

        // Zwracamy informacje o zakupionym bilecie
        return modelMapper.map(ticket, TicketDto.class);
    }


    public TicketDto validateTicket(Long ticketCode, Long vehicleId) {
        Ticket ticket = ticketRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("Ticket with id " + ticketCode + " not found."));

        ticket.control();

        Boolean isValid = checkTicketValidity(ticket, vehicleId);
        if (!isValid) {
            throw new IllegalArgumentException("Ticket with id " + ticketCode + " is not valid.");
        }

        ticketRepository.save(ticket);

        return modelMapper.map(ticket, TicketDto.class);
    }

//    public Boolean checkTicketValidity(Long ticketCode, Long vehicleId) {
//        Ticket ticket = ticketRepository.findByTicketCode(ticketCode)
//                .orElseThrow(() -> new NoSuchElementException("Ticket with id " + ticketCode + " not found."));
//
//        return checkTicketValidity(ticket, vehicleId);
//    }

    private Boolean checkTicketValidity(Ticket ticket, Long vehicleId) {
        return switch (ticket.getTicketType().getType()) {
            case SINGLE ->
                // For single tickets, check if the ticket was activated in the same vehicle
                    ticket.getIsActivated() && ticket.getVehicleId().equals(vehicleId);
            case TIME_LIMITED ->
                // For time-limited tickets, check if the ticket is still within its validity period
                    ticket.getIsActivated() && ticket.getExpirationDate().after(ticket.getValidityDate());
            case PERIOD ->
                // For period tickets, check if the current date is within the validity period
                    ticket.getPurchaseDate().before(ticket.getValidityDate()) && ticket.getExpirationDate().after(ticket.getValidityDate());
            default -> throw new IllegalArgumentException("Unsupported ticket type");
        };
    }


    @Scheduled(fixedRate = 3600000)
    public void deactivateExpiredTickets() {
        List<Ticket> allTickets = ticketRepository.findAll();
        Date now = new Date();

        for (Ticket ticket : allTickets) {
            if (ticket.getExpirationDate() == null) {
                ticket.setIsActivated(false);
                ticketRepository.delete(ticket);
            }
            if (ticket.getExpirationDate().before(now) && ticket.getIsActivated()) {
                ticket.setIsActivated(false);
                ticketRepository.delete(ticket);
            }
        }
    }

    public void deactivateExpiredTicket(Ticket ticket) {
        if (ticket.getTicketType().getType() == TicketTypeEnum.SINGLE && ticket.getExpirationDate() == null) {
            return;
        }

        Date now = new Date();

        if (ticket.getExpirationDate() != null && ticket.getExpirationDate().before(now) && ticket.getIsActivated()) {
            ticket.setIsActivated(false);
            ticketRepository.save(ticket);
        }
    }


//    @Scheduled(fixedRate = 60000)
//    public void notifyAboutExpiringTickets() {
//        Date fiveMinutesFromNow = new Date(System.currentTimeMillis() + 5*60*1000);
//        List<Ticket> expiringTickets = ticketRepository.findByExpirationDateBetween(new Date(), fiveMinutesFromNow);
//        for (Ticket ticket : expiringTickets) {
//            notificationService.notifyUser(ticket.getAccount(), "Your ticket is expiring soon!");
//        }
//    }


    public Date calculateExpirationDate(TicketType ticketType) {
        LocalDateTime now = LocalDateTime.now();

        if (ticketType.getType() == TicketTypeEnum.SINGLE) {
            return null;
        } else if (ticketType.getType() == TicketTypeEnum.TIME_LIMITED) {
            now = switch (ticketType.getTimeLimitedTicketType()) {
                case MIN_15 -> now.plusMinutes(15);
                case MIN_30 -> now.plusMinutes(30);
                case HOUR_1 -> now.plusHours(1);
                case HOUR_1_5 -> now.plusMinutes(90);
                case DAY_1 -> now.plusDays(1);
                case DAY_2 -> now.plusDays(2);
                case DAY_3 -> now.plusDays(3);
                case WEEK_1 -> now.plusWeeks(1);
                default -> throw new IllegalArgumentException("Unsupported time-limited ticket type");
            };
        } else if (ticketType.getType() == TicketTypeEnum.PERIOD) {
            now = switch (ticketType.getPeriodTicketType()) {
                case DAY_30 -> now.plusDays(30);
                case DAY_90 -> now.plusDays(90);
                case DAY_180 -> now.plusDays(180);
                case DAY_365 -> now.plusDays(365);
                default -> throw new IllegalArgumentException("Unsupported period ticket type");
            };
        } else {
            throw new IllegalArgumentException("Unsupported ticket type");
        }

        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getFormattedRemainingValidityTime(Ticket ticket) {
        if (ticket.getIsActivated()) {
            if (ticket.getTicketType().getType() != TicketTypeEnum.SINGLE) {
                long remainingTimeMillis = ticket.getExpirationDate().getTime() - System.currentTimeMillis();
                if (remainingTimeMillis > 0) {
                    long days = TimeUnit.MILLISECONDS.toDays(remainingTimeMillis);
                    long hours = TimeUnit.MILLISECONDS.toHours(remainingTimeMillis) % 24;
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTimeMillis) % 60;
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingTimeMillis) % 60;
                    return String.format("%d-%02d:%02d:%02d", days, hours, minutes, seconds);
                } else {
                    return "Expired";
                }
            } else {
                return "N/A";
            }
        } else {
            return "Inactive";
        }

    }
}


