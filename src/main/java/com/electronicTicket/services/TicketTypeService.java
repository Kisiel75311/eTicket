package com.electronicTicket.services;

import com.electronicTicket.dto.TicketTypeDto;
import com.electronicTicket.repositories.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;
    private final ModelMapper modelMapper;

    public List<TicketTypeDto> getAllTicketTypes() {
        return ticketTypeRepository.findAll().stream()
                .map(ticketType -> modelMapper.map(ticketType, TicketTypeDto.class))
                .toList();
    }
}

