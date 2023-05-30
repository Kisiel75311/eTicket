package com.electronicTicket.controllers;

import com.electronicTicket.dto.AccountDto;
import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.dto.TicketTypeDto;
import com.electronicTicket.dto.TransactionDto;
import com.electronicTicket.security.services.UserDetailsImpl;
import com.electronicTicket.services.AccountService;
import com.electronicTicket.services.TicketService;
import com.electronicTicket.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class PassengerController {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final TicketService ticketService;

    @PreAuthorize("hasRole('PASSENGER')")
    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDto>> getPassengerTickets(Principal principal) {
        Long id = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        List<TicketDto> ticketDtos = accountService.getPassengerTickets(id);
        return ResponseEntity.ok(ticketDtos);
    }
    @PreAuthorize("hasRole('PASSENGER')")
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> getPasengerTransactions(Principal principal) {
        Long id = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        List<TransactionDto> transactionDtos = accountService.getPasengerTransactions(id);
        return ResponseEntity.ok(transactionDtos);
    }
    @PreAuthorize("hasRole('PASSENGER')")
    @GetMapping("/transactions/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionDetails(@PathVariable Long transactionId, Principal principal) {
        TransactionDto transactionDto = transactionService.getTransactionDetails(transactionId);
        return ResponseEntity.ok(transactionDto);
    }
    @PreAuthorize("hasRole('PASSENGER')")
    @PostMapping("/buyTicket/{ticketTypeId}/{vehicleId}")
    public ResponseEntity<TicketDto> buyTicket(@PathVariable Long ticketTypeId, @PathVariable Long vehicleId, Principal principal) {
        Long id = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        TicketDto ticketDto = ticketService.buyTicket(id, ticketTypeId, vehicleId);
        return ResponseEntity.ok(ticketDto);
    }
    @PreAuthorize("hasRole('PASSENGER')")
    @PostMapping("/topUpAccount/{amount}")
    public ResponseEntity<AccountDto> addCredit(@PathVariable BigDecimal amount, Principal principal) {
        Long id = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        AccountDto accountDto = accountService.topUpAccount(id, amount);
        return ResponseEntity.ok(accountDto);
    }
}
