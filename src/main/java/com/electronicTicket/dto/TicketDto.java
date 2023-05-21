package com.electronicTicket.dto;

import lombok.*;

import java.util.Date;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private TicketTypeDto ticketType;
    private Date purchaseDate;
    private Date validityDate;
    private Boolean isStamped;
}

