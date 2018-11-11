package com.heanoria.reminders.securedapi.core.services;

import com.heanoria.reminders.securedapi.core.data.contexts.UserContext;
import com.heanoria.reminders.securedapi.core.data.mappers.UserContextMapper;
import com.heanoria.reminders.securedapi.database.repositories.ReactiveUserRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    private final ReactiveUserRepository userRepository;
    private final UserContextMapper userContextMapper;

    public UserServiceImpl(ReactiveUserRepository userRepository) {
        this.userRepository = userRepository;
        this.userContextMapper = new UserContextMapper();
    }

    public Mono<UserContext> findUserByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Email can not be null");
        return this.userRepository.findByEmail(email).map(this.userContextMapper::map);
    }

    public Mono<UserContext> findUserByUid(UUID uid) {
        return this.userRepository.findByUid(uid).map(this.userContextMapper::map);
    }

}
