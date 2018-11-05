package com.heanoria.reminders.securedapi.security.internal;

import com.heanoria.reminders.securedapi.security.data.UserProxy;
import com.heanoria.reminders.securedapi.security.proxies.UserServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class AuthentManager implements ReactiveAuthenticationManager {

    private final static Logger logger = LoggerFactory.getLogger(AuthentManager.class);

    private final UserServiceProxy userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthentManager(UserServiceProxy userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Authentication> authenticate(final Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return Mono.just(authentication);
        }
        return Mono.just(authentication)
                .switchIfEmpty(Mono.defer(this::raiseBadCredentials))
                .cast(UsernamePasswordAuthenticationToken.class)
                .flatMap(this::authenticateToken)
                .publishOn(Schedulers.parallel())
                .onErrorResume(it -> raiseBadCredentials())
                .filter(userDetails -> passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword()))
                .switchIfEmpty(Mono.defer(this::raiseBadCredentials))
                .map(UserAuthentication::new);
    }

    private <T> Mono<T> raiseBadCredentials() {
        return Mono.error(new BadCredentialsException("Invalid Credentials"));
    }

    private Mono<UserProxy> authenticateToken(final UsernamePasswordAuthenticationToken authenticationToken) {
        String username = authenticationToken.getName();

        logger.info("checking authentication for user " + username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("authenticated user " + username + ", setting security context");
            return this.userDetailsService.findByEmail(username);
        }

        return null;
    }
}
