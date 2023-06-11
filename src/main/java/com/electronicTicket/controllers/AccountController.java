package com.electronicTicket.controllers;

import com.electronicTicket.dto.AccountDto;
import com.electronicTicket.dto.Response;
import com.electronicTicket.security.services.UserDetailsImpl;
import com.electronicTicket.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<Response<List<AccountDto>>> getAllAccounts() {
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        Response<List<AccountDto>> response = new Response<>();
        response.setData(accountDtos);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/details")
    public ResponseEntity<Response<AccountDto>> getAccountById(Principal principal) {
        Long id = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        AccountDto accountDto = accountService.getAccountById(id);
        Response<AccountDto> response = new Response<>();
        response.setData(accountDto);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<Response<AccountDto>> createAccount(@RequestBody AccountDto accountDto) {
        accountService.createAccount(accountDto);
        Response<AccountDto> response = new Response<>();
        response.setData(accountDto);
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
