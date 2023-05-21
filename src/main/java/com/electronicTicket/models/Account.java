package com.electronicTicket.models;

import com.electronicTicket.models.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.math.BigDecimal;  // Import the BigDecimal class

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(unique = true, nullable = false)
    private String accountName;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private BigDecimal balance;  // Add the balance field

    @OneToMany(mappedBy = "account")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public Account(String accountName, String password, Role role, BigDecimal balance) {
        this.accountName = accountName;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }

}
