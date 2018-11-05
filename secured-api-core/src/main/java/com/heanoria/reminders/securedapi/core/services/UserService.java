package com.heanoria.reminders.securedapi.core.services;

import com.heanoria.reminders.securedapi.core.data.contexts.UserContext;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserContext> findUserByEmail(String email);
}
