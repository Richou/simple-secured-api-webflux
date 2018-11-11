package com.heanoria.reminders.securedapi.security.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserClaims {
    private final UUID id;
    private final String email;
    private final String username;
    private final List<String> roles;
}
