package com.heanoria.reminders.securedapi.security.internal;

import com.heanoria.reminders.securedapi.core.ports.SecurityPort;

import java.util.UUID;

public class SecurityAdapter implements SecurityPort {

    private final TokenHandler tokenHandler;

    public SecurityAdapter(TokenHandler tokenHandler) {
        this.tokenHandler = tokenHandler;
    }

    @Override
    public UUID getAuthenticatedUserId(String token) {
        return this.tokenHandler.getIdFromToken(token);
    }
}
