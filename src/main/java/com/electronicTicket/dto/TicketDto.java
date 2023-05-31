package com.electronicTicket.dto;

import lombok.*;

import java.util.Date;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long ticketCode;
    private TicketTypeDto ticketType;
    private Date purchaseDate;
    private Date validityDate;
    private Date expirationDate;
    private Boolean checked;
    private Boolean isActivated;
    private Long vehicleId;
    private String remainingValidityTime;
}

