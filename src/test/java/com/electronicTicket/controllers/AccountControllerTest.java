package com.electronicTicket.controllers;

import com.electronicTicket.dto.AccountDto;
import com.electronicTicket.models.Role;
import com.electronicTicket.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    private final AccountService accountService = mock(AccountService.class);
    private final AccountController accountController = new AccountController(accountService);

    @Test
    void getAllAccounts() {
        // Given
        when(accountService.getAllAccounts()).thenReturn(List.of(new AccountDto(), new AccountDto()));

        // When
        ResponseEntity<List<AccountDto>> response = accountController.getAllAccounts();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(accountService, times(1)).getAllAccounts();
    }

    @Test
    void getAccountById() {
        // Given
        Long id = 1L;
        when(accountService.getAccountById(id)).thenReturn(new AccountDto());

        // When
        ResponseEntity<AccountDto> response = accountController.getAccountById(id);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(accountService, times(1)).getAccountById(id);
    }

    @Test
    void createAccountTest() {
        // Given
        AccountDto accountDto = new AccountDto("test", "pass", Role.PASSENGER);

        // When
        ResponseEntity<AccountDto> response = accountController.createAccount(accountDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
        verify(accountService, times(1)).createAccount(accountDto);
    }
}
