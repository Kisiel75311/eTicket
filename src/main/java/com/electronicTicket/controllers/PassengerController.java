package com.electronicTicket.controllers;

import com.electronicTicket.dto.AccountDto;
import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.dto.TicketTypeDto;
import com.electronicTicket.dto.TransactionDto;
import com.electronicTicket.services.AccountService;
import com.electronicTicket.services.TicketService;
import com.electronicTicket.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class PassengerController {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final TicketService ticketService;

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

    @GetMapping("/{accountId}/transactions/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionDetails(@PathVariable Long accountId, @PathVariable Long transactionId) {
        TransactionDto transactionDto = transactionService.getTransactionDetails(transactionId);
        return ResponseEntity.ok(transactionDto);
    }

    @PostMapping("/{accountId}/buyTicket/{ticketTypeId}/{vehicleId}")
    public ResponseEntity<TicketDto> buyTicket(@PathVariable Long accountId, @PathVariable Long ticketTypeId, @PathVariable Long vehicleId) {
        TicketDto ticketDto = ticketService.buyTicket(accountId, ticketTypeId, vehicleId);
        return ResponseEntity.ok(ticketDto);
    }

    @PostMapping("/{accountId}/topUpAccount/{amount}")
    public ResponseEntity<AccountDto> addCredit(@PathVariable Long accountId, @PathVariable BigDecimal amount) {
        AccountDto accountDto = accountService.topUpAccount(accountId, amount);
        return ResponseEntity.ok(accountDto);
    }
}
