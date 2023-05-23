package com.electronicTicket.models;

import com.electronicTicket.models.enums.Role;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
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

    @Column
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.valueOf(0);  // Add the balance field

    @OneToMany(mappedBy = "account")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public Account(String accountName, String email, String password, Role role) {
        this.accountName = accountName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public Account(String accountName, String email, String password, BigDecimal balance, Role role) {
        this.accountName = accountName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }
}
