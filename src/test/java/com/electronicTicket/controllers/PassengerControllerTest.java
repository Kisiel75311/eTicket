package com.electronicTicket.controllers;

import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.dto.TicketTypeDto;
import com.electronicTicket.dto.TransactionDto;
import com.electronicTicket.services.AccountService;
import com.electronicTicket.services.TicketService;
import com.electronicTicket.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class PassengerControllerTest {

    private final AccountService accountService = mock(AccountService.class);
    private final TransactionService transaciotnService = mock(TransactionService.class);
    private final TicketService ticketService = mock(TicketService.class);
    private final PassengerController passengerController = new PassengerController(accountService, transaciotnService, ticketService);

    @Test
    void getPassengerTicketsTest() {
        // Given
        Long accountId = 1L;
        List<TicketDto> expectedTicketDtos = Arrays.asList(
                TicketDto.builder()
                        .ticketType(TicketTypeDto.builder().name("adult").price(10.0).build())
                        .purchaseDate(new Date())
                        .validityDate(new Date())
                        .build(),
                TicketDto.builder()
                        .ticketType(TicketTypeDto.builder().name("child").price(5.0).build())
                        .purchaseDate(new Date())
                        .validityDate(new Date())
                        .build()
        );
        when(accountService.getPassengerTickets(accountId)).thenReturn(expectedTicketDtos);

        // When
        ResponseEntity<List<TicketDto>> response = passengerController.getPassengerTickets(accountId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTicketDtos, response.getBody());
        verify(accountService, times(1)).getPassengerTickets(accountId);
    }

    @Test
    void getPassengerTicketsReturnsEmptyList() {
        // Given
        Long accountId = 1L;
        when(accountService.getPassengerTickets(accountId)).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<List<TicketDto>> response = passengerController.getPassengerTickets(accountId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
        verify(accountService, times(1)).getPassengerTickets(accountId);
    }

    @Test
    void getPasengerTransactionsTest() {
        // Given
        Long accountId = 1L;
        List<TransactionDto> expectedTransactionDtos = Arrays.asList(
                TransactionDto.builder()
                        .transactionDate(new Date())
                        .ticket(
                                TicketDto.builder()
                                        .ticketType(TicketTypeDto.builder().name("adult").price(10.0).build())
                                        .purchaseDate(new Date())
                                        .validityDate(new Date())
                                        .build()
                        )
                        .build(),
                TransactionDto.builder()
                        .transactionDate(new Date())
                        .ticket(
                                TicketDto.builder()
                                        .ticketType(TicketTypeDto.builder().name("child").price(5.0).build())
                                        .purchaseDate(new Date())
                                        .validityDate(new Date())
                                        .build()
                        )
                        .build()
        );
        when(accountService.getPasengerTransactions(accountId)).thenReturn(expectedTransactionDtos);

        // When
        ResponseEntity<List<TransactionDto>> response = passengerController.getPasengerTransactions(accountId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTransactionDtos, response.getBody());
        verify(accountService, times(1)).getPasengerTransactions(accountId);
    }

    @Test
    void getPasengerTransactionsReturnsEmptyList() {
        // Given
        Long accountId = 1L;
        when(accountService.getPasengerTransactions(accountId)).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<List<TransactionDto>> response = passengerController.getPasengerTransactions(accountId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
        verify(accountService, times(1)).getPasengerTransactions(accountId);
    }
}
