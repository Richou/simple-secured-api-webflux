package com.heanoria.reminders.securedapi.security.internal;

import com.heanoria.reminders.securedapi.security.data.UserProxy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication implements Authentication {

    private final UserProxy user;
    private boolean authenticated = true;

    public UserAuthentication(UserProxy user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public UserProxy getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user.getEmail();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    public Authentication getAuthentication() {
        return this;
    }
}
