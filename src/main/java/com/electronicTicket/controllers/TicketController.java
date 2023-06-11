package com.electronicTicket.controllers;

import com.electronicTicket.dto.Response;
import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.dto.TicketTypeDto;
import com.electronicTicket.repositories.TicketRepository;
import com.electronicTicket.repositories.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketTypeRepository ticketTypeRepository;
    private final ModelMapper modelMapper;
    private final TicketRepository ticketRepository;

    @GetMapping("/types")
    public ResponseEntity<Response<List<TicketTypeDto>>> getAllTicketTypes() {
        List<TicketTypeDto> ticketTypeDtos = ticketTypeRepository.findAll().stream()
                .map(ticketType -> modelMapper.map(ticketType, TicketTypeDto.class))
                .toList();
        Response<List<TicketTypeDto>> response = new Response<>();
        response.setData(ticketTypeDtos);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<Response<List<TicketDto>>> getAllTickets() {
        List<TicketDto> ticketDtos = ticketRepository.findAll().stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .toList();
        Response<List<TicketDto>> response = new Response<>();
        response.setData(ticketDtos);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
