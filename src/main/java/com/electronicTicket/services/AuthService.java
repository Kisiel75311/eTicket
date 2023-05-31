package com.electronicTicket.services;

import com.electronicTicket.models.Account;
import com.electronicTicket.models.enums.Role;
import com.electronicTicket.repositories.AccountRepository;
import com.electronicTicket.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public Account saveAccount(Account account){
        account.setPassword(account.getPassword());
        return accountRepository.save(account);
    }

    public Boolean existsByUsername(String username){
        return accountRepository.existsByAccountName(username);
    }

    public Boolean isAdmin(String token){
        String username = jwtUtils.getUserNameFromJwtToken(token);
        Account account = accountRepository.findByAccountName(username).
                orElseThrow(() -> new RuntimeException("Error: Account is not found."));
        return account != null && account.getRole().equals(Role.ROLE_ADMIN);
    }
}
