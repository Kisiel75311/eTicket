package com.electronicTicket.dto;

import java.util.List;

public class PassengerDto extends AccountDto {
    private List<TicketDto> tickets;
    private List<TransactionDto> transactions;
}

