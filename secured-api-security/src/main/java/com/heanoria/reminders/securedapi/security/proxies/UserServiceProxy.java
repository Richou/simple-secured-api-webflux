package com.heanoria.reminders.securedapi.security.proxies;

import com.heanoria.reminders.securedapi.core.data.contexts.UserContext;
import com.heanoria.reminders.securedapi.core.services.UserService;
import com.heanoria.reminders.securedapi.security.data.UserProxy;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public class UserServiceProxy implements ReactiveUserDetailsService {

    private final UserService userService;

    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    public UserContext getUserByMail(String email) {
        return this.userService.findUserByEmail(email).block();
    }

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        return this.userService.findUserByEmail(email).map(UserProxy::new);
    }

    public Mono<UserProxy> findByEmail(String email) {
        return this.userService.findUserByEmail(email).map(UserProxy::new);
    }
}
