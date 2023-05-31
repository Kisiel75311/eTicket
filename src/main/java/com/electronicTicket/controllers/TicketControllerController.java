package com.electronicTicket.controllers;

import com.electronicTicket.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket-controller")
@RequiredArgsConstructor
public class TicketControllerController {

    private final TicketService ticketService;

    @PreAuthorize("hasRole('CONTROLLER')")
    @GetMapping("/validate-ticket")
    public ResponseEntity<Boolean> validateTicket(@RequestParam Long ticketCode, @RequestParam Long vehicleId) {
        boolean isValid = ticketService.checkTicketValidity(ticketCode, vehicleId);
        return ResponseEntity.ok(isValid);
    }
}
