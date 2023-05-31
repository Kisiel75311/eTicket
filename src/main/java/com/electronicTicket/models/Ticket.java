package com.electronicTicket.models;

import javax.persistence.*;
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
    private Date validityDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    private Boolean checked = false;

    private Long vehicleId = null;

    @ManyToOne
    private Account account;

    private Boolean isActivated = false;

    public void activate(Long vehicleId) {
        this.isActivated = true;
        this.vehicleId = vehicleId;
    }
    public void control() {
        this.checked = true;
        this.validityDate = new Date();
    }
}
