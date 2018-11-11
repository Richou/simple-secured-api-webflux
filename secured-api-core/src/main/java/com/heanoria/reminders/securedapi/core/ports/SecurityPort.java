package com.heanoria.reminders.securedapi.core.ports;

import java.util.UUID;

public interface SecurityPort {
    UUID getAuthenticatedUserId(String token);
}
