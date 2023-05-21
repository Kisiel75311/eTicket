package com.electronicTicket.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketTypeId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;
}
