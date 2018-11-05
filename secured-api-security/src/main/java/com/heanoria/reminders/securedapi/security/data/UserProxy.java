package com.heanoria.reminders.securedapi.security.data;

import com.heanoria.reminders.securedapi.core.data.contexts.UserContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserProxy implements UserDetails {

    private final UserContext userContext;

    public UserProxy(UserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userContext.getAuthorities().stream().map(it -> (GrantedAuthority) it::getAuthority).collect(Collectors.toList());
    }

    public UUID getId() {
        return userContext.getId();
    }

    @Override
    public String getPassword() {
        return userContext.getPassword();
    }

    @Override
    public String getUsername() {
        return userContext.getUsername();
    }

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

    public String getEmail() {
        return userContext.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
