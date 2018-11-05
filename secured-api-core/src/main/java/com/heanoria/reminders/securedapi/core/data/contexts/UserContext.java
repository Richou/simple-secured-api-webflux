package com.heanoria.reminders.securedapi.core.data.contexts;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserContext {

    private final UUID id;
    private final String email;
    private final String username;
    private final String password;
    private final List<RoleContext> authorities;
}
