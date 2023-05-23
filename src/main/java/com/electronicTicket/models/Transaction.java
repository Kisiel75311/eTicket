package com.electronicTicket.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private Account account;

}
