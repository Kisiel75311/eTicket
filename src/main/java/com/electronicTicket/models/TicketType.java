package com.electronicTicket.models;

import com.electronicTicket.models.enums.DiscountTypeEnum;
import com.electronicTicket.models.enums.PeriodTicketTypeEnum;
import com.electronicTicket.models.enums.TicketTypeEnum;
import com.electronicTicket.models.enums.TimeLimitedTicketTypeEnum;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

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
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountTypeEnum discountType;

    @Enumerated(EnumType.STRING)
    private TimeLimitedTicketTypeEnum timeLimitedTicketType;  // Może być null, jeśli nie dotyczy

    @Enumerated(EnumType.STRING)
    private PeriodTicketTypeEnum periodTicketType;  // Może być null, jeśli nie dotyczy
}
