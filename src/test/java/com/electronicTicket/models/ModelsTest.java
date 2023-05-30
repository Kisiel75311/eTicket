package com.electronicTicket.models;

import com.electronicTicket.models.enums.Role;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ModelsTest {

    @Test
    void accountTest() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountName("testAccount");
        account.setPassword("password");
        account.setRole(Role.ROLE_PASSENGER);

        assertEquals(1L, account.getAccountId());
        assertEquals("testAccount", account.getAccountName());
        assertEquals("password", account.getPassword());
        assertEquals(Role.ROLE_PASSENGER, account.getRole());
    }

    @Test
    void ticketTest() {
        Ticket ticket = new Ticket();
        Date now = new Date();
        ticket.setTicketCode(1L);
        ticket.setPurchaseDate(now);
        ticket.setExpirationDate(now);
        ticket.setIsActivated(true);

        assertEquals(1L, ticket.getTicketCode());
        assertEquals(now, ticket.getPurchaseDate());
        assertEquals(now, ticket.getExpirationDate());
        assertTrue(ticket.getIsActivated());
    }

    @Test
    void ticketTypeTest() {
        TicketType ticketType = new TicketType();
        ticketType.setTicketTypeId(1L);
        ticketType.setName("Adult");
        ticketType.setPrice(BigDecimal.valueOf(2.5));

        assertEquals(1L, ticketType.getTicketTypeId());
        assertEquals("Adult", ticketType.getName());
        assertEquals(BigDecimal.valueOf(2.5), ticketType.getPrice());
    }

    @Test
    void transactionTest() {
        Transaction transaction = new Transaction();
        Date now = new Date();
        transaction.setTransactionId(1L);
        transaction.setTransactionDate(now);

        assertEquals(1L, transaction.getTransactionId());
        assertEquals(now, transaction.getTransactionDate());
    }
}

