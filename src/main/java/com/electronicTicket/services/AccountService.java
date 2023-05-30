package com.electronicTicket.services;

import com.electronicTicket.dto.AccountDto;
import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.dto.TransactionDto;
import com.electronicTicket.models.Account;
import com.electronicTicket.models.enums.Role;
import com.electronicTicket.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TicketService ticketService;

    private final ModelMapper modelMapper;

    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream()
                .map(account -> modelMapper.map(account, AccountDto.class))
                .collect(Collectors.toList());
    }

    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findByAccountId(id).orElseThrow(() -> new NoSuchElementException("Account with id " + id + " not found."));

        return modelMapper.map(account, AccountDto.class);
    }

    public AccountDto createAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        accountRepository.save(account);
        return accountDto;
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public List<TicketDto> getPassengerTickets(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account with id " + id + " not found."));
        if (account.getRole() != Role.ROLE_PASSENGER) {
            throw new IllegalArgumentException("Only accounts with role PASSENGER can check tickets.");
        }

        return account.getTickets().stream()
                .map(ticket -> {
                    ticketService.deactivateExpiredTicket(ticket); // aktualizuj stan biletu przed zwróceniem go
                    TicketDto ticketDto = modelMapper.map(ticket, TicketDto.class);
                    ticketDto.setRemainingValidityTime(ticketService.getFormattedRemainingValidityTime(ticket));
                    return ticketDto;
                })
                .collect(Collectors.toList());
    }

    public List<TransactionDto> getPasengerTransactions(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account with id " + id + " not found."));
        if (account.getRole() != Role.ROLE_PASSENGER) {
            throw new IllegalArgumentException("Only accounts with role PASSENGER can check transactions.");
        }
        return account.getTransactions().stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

    public AccountDto topUpAccount(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("Account with id " + accountId + " not found."));

        if (account.getRole() != Role.ROLE_PASSENGER) {
            throw new IllegalArgumentException("Only accounts with role PASSENGER can top up account.");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Top up amount must be positive.");
        }

        account.setBalance(account.getBalance().add(amount));

        accountRepository.save(account);

        return modelMapper.map(account, AccountDto.class);
    }
}
