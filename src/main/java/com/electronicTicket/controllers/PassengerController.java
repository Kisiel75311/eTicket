package com.electronicTicket.controllers;

import com.electronicTicket.dto.*;
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
import java.time.LocalDateTime;
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
    public ResponseEntity<Response<List<TicketDto>>> getPassengerTickets(Principal principal) {
        Long id = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        List<TicketDto> ticketDtos = accountService.getPassengerTickets(id);
        Response<List<TicketDto>> response = new Response<>();
        response.setData(ticketDtos);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('PASSENGER')")
    @GetMapping("/transactions")
    public ResponseEntity<Response<List<TransactionDto>>> getPasengerTransactions(Principal principal) {
        Long id = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        List<TransactionDto> transactionDtos = accountService.getPasengerTransactions(id);
        Response<List<TransactionDto>> response = new Response<>();
        response.setData(transactionDtos);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('PASSENGER')")
    @GetMapping("/transactions/{transactionId}")
    public ResponseEntity<Response<TransactionDto>> getTransactionDetails(@PathVariable Long transactionId, Principal principal) {
        TransactionDto transactionDto = transactionService.getTransactionDetails(transactionId);
        Response<TransactionDto> response = new Response<>();
        response.setData(transactionDto);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('PASSENGER')")
    @PostMapping("/buyTicket/{ticketTypeId}/{vehicleId}")
    public ResponseEntity<Response<TicketDto>> buyTicket(@PathVariable Long ticketTypeId, @PathVariable Long vehicleId, Principal principal) {
        Long id = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        TicketDto ticketDto = ticketService.buyTicket(id, ticketTypeId, vehicleId);
        Response<TicketDto> response = new Response<>();
        response.setData(ticketDto);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('PASSENGER')")
    @PostMapping("/topUpAccount/{amount}")
    public ResponseEntity<Response<AccountDto>> addCredit(@PathVariable BigDecimal amount, Principal principal) {
        Long id = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        AccountDto accountDto = accountService.topUpAccount(id, amount);
        Response<AccountDto> response = new Response<>();
        response.setData(accountDto);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
