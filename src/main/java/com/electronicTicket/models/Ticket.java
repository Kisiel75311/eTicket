package com.electronicTicket.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketCode;

    @ManyToOne
    private TicketType ticketType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    private Boolean checked = false;

    private Long vehicleId = null;  // Set to null initially

    @ManyToOne
    private Account account;

    private Boolean isActivated = false;  // This will be set to true when the ticket is used

    // This method can be used to activate the ticket and set the vehicle ID
    public void activate(Long vehicleId) {
        this.isActivated = true;
        this.vehicleId = vehicleId;
    }
}
