package com.electronicTicket.controllers;

import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.dto.TicketTypeDto;
import com.electronicTicket.repositories.TicketRepository;
import com.electronicTicket.repositories.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")  // Adjust as needed
@RequiredArgsConstructor
public class TicketController {

    private final TicketTypeRepository ticketTypeRepository;
    private final ModelMapper modelMapper;
    private final TicketRepository ticketRepository;

    @GetMapping("/types")
    public List<TicketTypeDto> getAllTicketTypes() {
        return ticketTypeRepository.findAll().stream()
                .map(ticketType -> modelMapper.map(ticketType, TicketTypeDto.class))
                .toList();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<TicketDto> getAllTickets() {
        System.out.println(ticketRepository.findAll());
        return ticketRepository.findAll().stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .toList();
    }
}
