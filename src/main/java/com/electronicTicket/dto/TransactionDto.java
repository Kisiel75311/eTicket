package com.electronicTicket.dto;

import lombok.*;

import java.util.Date;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Date transactionDate;
    private TicketDto ticket;
}
