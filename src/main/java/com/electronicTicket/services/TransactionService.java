package com.electronicTicket.services;

import com.electronicTicket.dto.TransactionDto;
import com.electronicTicket.models.Account;
import com.electronicTicket.models.Ticket;
import com.electronicTicket.models.Transaction;
import com.electronicTicket.repositories.AccountRepository;
import com.electronicTicket.repositories.TicketRepository;
import com.electronicTicket.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    public TransactionDto registerTransaction(Long accountId, Long ticketId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account with id " + accountId + " does not exist."));
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Ticket with id " + ticketId + " does not exist."));

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(new Date());
        transaction.setAccount(account);
        transaction.setTicket(ticket);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return modelMapper.map(savedTransaction, TransactionDto.class);
    }

    public TransactionDto getTransactionDetails(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new IllegalArgumentException("Transaction with id " + transactionId + " does not exist."));
        return modelMapper.map(transaction, TransactionDto.class);
    }
}
