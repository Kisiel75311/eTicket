package com.electronicTicket.controllers;

import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.dto.TransactionDto;
import com.electronicTicket.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class PassengerController {

    private final AccountService accountService;
    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<TicketDto>> getPassengerTickets(@PathVariable Long id) {
        List<TicketDto> ticketDtos = accountService.getPassengerTickets(id);
        return ResponseEntity.ok(ticketDtos);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> getPasengerTransactions(@PathVariable Long id) {
        List<TransactionDto> transactionDtos = accountService.getPasengerTransactions(id);
        return ResponseEntity.ok(transactionDtos);
    }
}
