package com.electronicTicket.dto;

import com.electronicTicket.models.enums.Role;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DtoTests {

    @Test
    public void createAndManipulateAccountDto() {
        AccountDto accountDto = AccountDto.builder()
                .accountName("testName")
                .role(Role.PASSENGER)
                .build();

        assertEquals("testName", accountDto.getAccountName());
        assertEquals(Role.PASSENGER, accountDto.getRole());


        accountDto.setAccountName("testName2");
        assertEquals("testName2", accountDto.getAccountName());
    }

    @Test
    public void createAndManipulateTicketDto() {
        Date date = new Date();
        TicketTypeDto ticketTypeDto = TicketTypeDto.builder()
                .name("adult")
                .price(3.5)
                .build();

        TicketDto ticketDto = TicketDto.builder()
                .ticketType(ticketTypeDto)
                .purchaseDate(date)
                .validityDate(date)
                .build();

        assertEquals(ticketTypeDto, ticketDto.getTicketType());
        assertEquals(date, ticketDto.getPurchaseDate());
        assertEquals(date, ticketDto.getValidityDate());

        ticketTypeDto.setName("child");
        ticketDto.setTicketType(ticketTypeDto);
        assertEquals("child", ticketDto.getTicketType().getName());
    }

    @Test
    public void createAndManipulateTicketTypeDto() {
        TicketTypeDto ticketTypeDto = TicketTypeDto.builder()
                .name("child")
                .price(1.75)
                .build();

        assertEquals("child", ticketTypeDto.getName());
        assertEquals(1.75, ticketTypeDto.getPrice());

        ticketTypeDto.setName("senior");
        assertEquals("senior", ticketTypeDto.getName());
    }

    @Test
    public void createAndManipulateTransactionDto() {
        Date date = new Date();
        TicketTypeDto ticketTypeDto = TicketTypeDto.builder()
                .name("adult")
                .price(3.5)
                .build();

        TicketDto ticketDto = TicketDto.builder()
                .ticketType(ticketTypeDto)
                .purchaseDate(date)
                .validityDate(date)
                .build();

        TransactionDto transactionDto = TransactionDto.builder()
                .transactionDate(date)
                .ticket(ticketDto)
                .build();

        assertEquals(date, transactionDto.getTransactionDate());
        assertEquals(ticketDto, transactionDto.getTicket());

        Date newDate = new Date();
        transactionDto.setTransactionDate(newDate);
        assertEquals(newDate, transactionDto.getTransactionDate());
    }
}
