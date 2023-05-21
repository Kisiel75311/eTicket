package com.electronicTicket.services;

import com.electronicTicket.dto.AccountDto;
import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.dto.TransactionDto;
import com.electronicTicket.models.Account;
import com.electronicTicket.models.Role;
import com.electronicTicket.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

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
        if (account.getRole() != Role.PASSENGER) {
            throw new IllegalArgumentException("Only accounts with role PASSENGER can check tickets.");
        }
        return account.getTickets().stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    public List<TransactionDto> getPasengerTransactions(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account with id " + id + " not found."));
        if (account.getRole() != Role.PASSENGER) {
            throw new IllegalArgumentException("Only accounts with role PASSENGER can check transactions.");
        }
        return account.getTransactions().stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDto.class))
                .collect(Collectors.toList());
    }

}
