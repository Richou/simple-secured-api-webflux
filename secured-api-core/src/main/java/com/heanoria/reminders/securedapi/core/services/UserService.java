package com.heanoria.reminders.securedapi.core.services;

import com.heanoria.reminders.securedapi.core.data.contexts.UserContext;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService {
    Mono<UserContext> findUserByEmail(String email);
    Mono<UserContext> findUserByUid(UUID uid);
}
