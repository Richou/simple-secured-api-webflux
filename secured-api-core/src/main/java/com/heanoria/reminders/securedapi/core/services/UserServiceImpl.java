package com.heanoria.reminders.securedapi.core.services;

import com.heanoria.reminders.securedapi.core.data.contexts.UserContext;
import com.heanoria.reminders.securedapi.core.data.mappers.UserContextMapper;
import com.heanoria.reminders.securedapi.database.repositories.UserRepository;
import reactor.core.publisher.Mono;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserContextMapper userContextMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userContextMapper = new UserContextMapper();
    }

    public Mono<UserContext> findUserByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Email can not be null");
        return this.userRepository.findByEmail(email).map(this.userContextMapper::map);
    }
}
