package com.electronicTicket.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeDto {
    private Long id;
    private String name;
    private Double price;
}

