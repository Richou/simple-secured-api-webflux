package com.heanoria.reminders.securedapi.security.controllers;

import com.heanoria.reminders.securedapi.core.data.dto.Credentials;
import com.heanoria.reminders.securedapi.security.data.Token;
import com.heanoria.reminders.securedapi.security.internal.AuthentManager;
import com.heanoria.reminders.securedapi.security.internal.TokenHandler;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class LoginController {

    private final TokenHandler tokenHandler;
    private final AuthentManager authentManager;

    public LoginController(TokenHandler tokenHandler, AuthentManager authentManager) {
        this.tokenHandler = tokenHandler;
        this.authentManager = authentManager;
    }

    @PostMapping("/login")
    public Mono<Token> doPostLogin(@RequestBody Credentials credentials) {
        Authentication authentToken = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());

        Mono<Authentication> authentication = this.authentManager.authenticate(authentToken)
                .doOnError(error -> {throw new BadCredentialsException("Bad Credentials");});
        ReactiveSecurityContextHolder.withAuthentication(authentToken);

        return authentication.map(authent -> Token.builder().token(tokenHandler.createTokenForUser(tokenHandler.mapAuthenticationToUserClaims(authent))).build());
    }
}
