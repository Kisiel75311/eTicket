package com.electronicTicket.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.electronicTicket.models.Account;
import com.electronicTicket.repositories.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountRepository.findByAccountName(accountName)
                .orElseThrow(() -> new UsernameNotFoundException("Account Not Found with accountName: " + accountName));

        return UserDetailsImpl.build(account);
    }
}
