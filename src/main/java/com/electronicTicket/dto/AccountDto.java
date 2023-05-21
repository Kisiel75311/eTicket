package com.electronicTicket.dto;

import com.electronicTicket.models.Role;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Collections;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String accountName;
    private String password;
    private Role role;
}

