package com.electronicTicket.database;

import com.electronicTicket.models.enums.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.electronicTicket.models.*;
import com.electronicTicket.repositories.*;

import java.util.Arrays;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final TicketRepository ticketRepository;
    private final TransactionRepository transactionRepository;
    private final TicketTypeRepository ticketTypeRepository;

    public DatabaseLoader(AccountRepository accountRepository, TicketRepository ticketRepository, TransactionRepository transactionRepository, TicketTypeRepository ticketTypeRepository) {
        this.accountRepository = accountRepository;
        this.ticketRepository = ticketRepository;
        this.transactionRepository = transactionRepository;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Override
    public void run(String... strings) {
        Account account1 = new Account("admin", "admin@admin", "admin", new BigDecimal(100), Role.ADMIN);
        accountRepository.save(account1);

        generateAllTicketTypes();

    }

    private void generateAllTicketTypes() {
        BigDecimal basePrice = BigDecimal.TEN;
        List<DiscountTypeEnum> discountTypes = Arrays.asList(DiscountTypeEnum.values());
        List<TimeLimitedTicketTypeEnum> timeLimitedTicketTypes = Arrays.asList(TimeLimitedTicketTypeEnum.values());
        List<PeriodTicketTypeEnum> periodTicketTypes = Arrays.asList(PeriodTicketTypeEnum.values());

        discountTypes.forEach(discountType -> {
            timeLimitedTicketTypes.forEach(timeLimitedTicketType -> {
                TicketType timeLimitedTicket = new TicketType(null, "Time Limited " + discountType.name() + " " + timeLimitedTicketType.name(),
                        basePrice, TicketTypeEnum.TIME_LIMITED, discountType, timeLimitedTicketType, null);
                ticketTypeRepository.save(timeLimitedTicket);
            });

            periodTicketTypes.forEach(periodTicketType -> {
                TicketType periodTicket = new TicketType(null, "Period " + discountType.name() + " " + periodTicketType.name(),
                        basePrice, TicketTypeEnum.PERIOD, discountType, null, periodTicketType);
                ticketTypeRepository.save(periodTicket);
            });

            TicketType singleTicket = new TicketType(null, "Single " + discountType.name(),
                    basePrice, TicketTypeEnum.SINGLE, discountType, null, null);
            ticketTypeRepository.save(singleTicket);
        });
    }
}
