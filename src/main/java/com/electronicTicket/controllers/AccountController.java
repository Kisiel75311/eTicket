package com.electronicTicket.controllers;

import com.electronicTicket.dto.AccountDto;
import com.electronicTicket.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        accountService.createAccount(accountDto);
        return ResponseEntity.ok(accountDto);
    }
}
