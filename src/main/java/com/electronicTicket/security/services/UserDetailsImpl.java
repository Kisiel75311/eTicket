package com.electronicTicket.security.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.electronicTicket.models.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

@AllArgsConstructor
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private static final Long serialVersionUID = 1L;

    private Long id;

    private String accountName;

    private String email;

    @JsonIgnore
    private String password;

    private BigDecimal balance;

    private Collection<? extends GrantedAuthority> authorities;


    public static UserDetailsImpl build(Account account) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().name()));

        return new UserDetailsImpl(
                account.getAccountId(),
                account.getAccountName(),
                account.getEmail(),
                account.getPassword(),
                account.getBalance(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() { return accountName; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
