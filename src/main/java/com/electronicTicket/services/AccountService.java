package com.electronicTicket.services;

import com.electronicTicket.dto.AccountDto;
import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.models.Account;
import com.electronicTicket.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;

    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findByAccountId(id).orElseThrow(() -> new NoSuchElementException("Account with id " + id + " not found."));

        return modelMapper.map(account, AccountDto.class);
    }

    public void createAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        accountRepository.save(account);
    }

    public List<TicketDto> getPassengerTickets(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account with id " + id + " not found."));

        return account.getTickets().stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }


}
