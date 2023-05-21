package com.electronicTicket.services;

import com.electronicTicket.dto.AccountDto;
import com.electronicTicket.dto.TicketDto;
import com.electronicTicket.dto.TransactionDto;
import com.electronicTicket.models.*;
import com.electronicTicket.models.enums.Role;
import com.electronicTicket.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testGetAllAccounts() {
        AccountService service = new AccountService(accountRepository, modelMapper);
        Account account = new Account(1L, "test", "password", Role.PASSENGER, null, null, null);
        AccountDto accountDto = new AccountDto("test", Role.PASSENGER);

        when(accountRepository.findAll()).thenReturn(Arrays.asList(account));
        when(modelMapper.map(account, AccountDto.class)).thenReturn(accountDto);

        assertEquals(1, service.getAllAccounts().size());
    }

    @Test
    public void testGetAccountById() {
        AccountService service = new AccountService(accountRepository, modelMapper);
        Account account = new Account(1L, "test", "password", Role.PASSENGER, null, null, null);
        AccountDto accountDto = new AccountDto("test", Role.PASSENGER);

        when(accountRepository.findByAccountId(1L)).thenReturn(Optional.of(account));
        when(modelMapper.map(account, AccountDto.class)).thenReturn(accountDto);

        assertEquals("test", service.getAccountById(1L).getAccountName());
    }

    @Test
    public void testCreateAccount() {
        AccountService service = new AccountService(accountRepository, modelMapper);
        Account account = new Account(1L, "test", "password", Role.PASSENGER, null, null, null);
        AccountDto accountDto = new AccountDto("test", Role.PASSENGER);

        when(modelMapper.map(accountDto, Account.class)).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);

        assertEquals(accountDto, service.createAccount(accountDto));
    }

    @Test
    public void testDeleteAccount() {
        AccountService service = new AccountService(accountRepository, modelMapper);
        doNothing().when(accountRepository).deleteById(1L);

        service.deleteAccount(1L);

        verify(accountRepository, times(1)).deleteById(1L);
    }

//    @Test
//    public void testGetPassengerTickets() {
//        AccountService service = new AccountService(accountRepository, modelMapper);
//        Ticket ticket1 = new Ticket(1L, null, null, null, false, null, null);
//        List<Ticket> tickets = new ArrayList<>();
//        tickets.add(ticket1);
//        Account account = new Account(1L, "test", "password", Role.PASSENGER, null, tickets, null);
//        TicketDto ticketDto1 = new TicketDto(null, null, null, false, null);
//
//        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
//        when(modelMapper.map(ticket1, TicketDto.class)).thenReturn(ticketDto1);
//
//        assertEquals(1, service.getPassengerTickets(1L).size());
//    }



    @Test
    public void testGetPassengerTransactions() {
        AccountService service = new AccountService(accountRepository, modelMapper);

        // Tworzenie transakcji
        Transaction transaction1 = new Transaction(1L, null, null, null);

        // Dodanie transakcji do listy
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);

        // Utworzenie konta z listą transakcji
        Account account = new Account(1L, "test", "password", Role.PASSENGER, null, null, transactions);

        // Mapowanie transakcji na obiekty DTO
        TransactionDto transactionDto1 = new TransactionDto(null, null);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(modelMapper.map(transaction1, TransactionDto.class)).thenReturn(transactionDto1);

        assertEquals(1, service.getPasengerTransactions(1L).size());
    }


    @Test
    public void testGetAccountByIdThrowsNoSuchElementException() {
        AccountService service = new AccountService(accountRepository, modelMapper);
        when(accountRepository.findByAccountId(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            service.getAccountById(1L);
        });
    }

    @Test
    public void testGetPassengerTicketsThrowsNoSuchElementException() {
        AccountService service = new AccountService(accountRepository, modelMapper);
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            service.getPassengerTickets(1L);
        });
    }

    @Test
    public void testGetPasengerTransactionsThrowsNoSuchElementException() {
        AccountService service = new AccountService(accountRepository, modelMapper);
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            service.getPasengerTransactions(1L);
        });
    }

    @Test
    public void testGetPassengerTicketsThrowsIllegalArgumentException() {
        AccountService service = new AccountService(accountRepository, modelMapper);
        Account account = new Account(1L, "test", "password", Role.PASSENGER, null, null, null);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(IllegalArgumentException.class, () -> {
            service.getPassengerTickets(1L);
        });
    }

    @Test
    public void testGetPasengerTransactionsThrowsIllegalArgumentException() {
        AccountService service = new AccountService(accountRepository, modelMapper);
        Account account = new Account(1L, "test", "password", Role.PASSENGER, null, null, null);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        assertThrows(IllegalArgumentException.class, () -> {
            service.getPasengerTransactions(1L);
        });

    }
}
