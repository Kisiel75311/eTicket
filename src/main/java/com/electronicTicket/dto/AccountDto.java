package com.electronicTicket.dto;

import com.electronicTicket.models.enums.Role;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String accountName;
//    private String password;
    private Role role;
    private BigDecimal balance;
}

