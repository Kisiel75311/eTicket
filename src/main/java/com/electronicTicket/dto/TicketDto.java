package com.electronicTicket.dto;

import java.util.Date;

public class TicketDto {
    private String ticketCode;
    private TicketTypeDto ticketType;
    private Date purchaseDate;
    private Date validityDate;
    private Boolean isStamped;
}

