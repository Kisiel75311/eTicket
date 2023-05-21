package com.electronicTicket.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.electronicTicket.models.*;
import com.electronicTicket.repositories.*;

import java.util.Date;

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
        Account account1 = new Account(null, "testAccount1", "password1", Role.PASSENGER, null, null);
        Account account2 = new Account(null, "testAccount2", "password2", Role.CONTROLLER, null, null);
        accountRepository.save(account1);
        accountRepository.save(account2);

        TicketType ticketType1 = new TicketType(null, "TestType1", 15.0);
        TicketType ticketType2 = new TicketType(null, "TestType2", 20.0);
        ticketTypeRepository.save(ticketType1);
        ticketTypeRepository.save(ticketType2);

        Date currentTimestamp = new Date();

        Ticket ticket1 = new Ticket(null, ticketType1, currentTimestamp, new Date(currentTimestamp.getTime() + (1000 * 60 * 60 * 24)), true, account1);
        Ticket ticket2 = new Ticket(null, ticketType2, currentTimestamp, new Date(currentTimestamp.getTime() + (1000 * 60 * 60 * 24)), false, account2);
        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);

        Transaction transaction1 = new Transaction(null, currentTimestamp, ticket1, account1);
        Transaction transaction2 = new Transaction(null, currentTimestamp, ticket2, account2);
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
    }
}

