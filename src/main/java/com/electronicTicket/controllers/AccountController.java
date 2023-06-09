package com.electronicTicket.controllers;

import com.electronicTicket.dto.AccountDto;
import com.electronicTicket.payload.response.UserInfoResponse;
import com.electronicTicket.security.services.UserDetailsImpl;
import com.electronicTicket.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }

    @GetMapping("/details")
    public ResponseEntity<UserInfoResponse> getAccountById(Principal principal) {
        Long id = ((UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getId();
        AccountDto accountDto = accountService.getAccountById(id);
        UserInfoResponse userInfoResponse = new UserInfoResponse(
                id,
                accountDto.getAccountName(),
                accountDto.getEmail(),
                List.of(accountDto.getRole().name()),
                accountDto.getBalance()
        );
        return ResponseEntity.ok(userInfoResponse);
    }

    @PostMapping("/")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        accountService.createAccount(accountDto);
        return ResponseEntity.ok(accountDto);
    }
}
