package com.heanoria.reminders.securedapi.security.internal;

import com.heanoria.reminders.securedapi.security.proxies.UserServiceProxy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class TokenAuthenticationConverter implements Function<ServerWebExchange, Mono<Authentication>> {

    private final TokenHandler tokenHandler;
    private final UserServiceProxy userServiceProxy;

    public TokenAuthenticationConverter(TokenHandler tokenHandler, UserServiceProxy userServiceProxy) {
        this.tokenHandler = tokenHandler;
        this.userServiceProxy = userServiceProxy;
    }

    @Override
    public Mono<Authentication> apply(ServerWebExchange serverWebExchange) {
        return Mono.justOrEmpty(serverWebExchange)
                .map(this::getTokenFromRequest)
                .filter(Objects::nonNull)
                .filter(token -> !StringUtils.isEmpty(token))
                .map(tokenHandler::getEmailFromToken)
                .flatMap(this.userServiceProxy::findByEmail)
                .map(UserAuthentication::new)
                .map(UserAuthentication::getAuthentication)
                .filter(Objects::nonNull);
    }

    private String getTokenFromRequest(ServerWebExchange serverWebExchange) {
        String token = serverWebExchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);
        return StringUtils.isEmpty(token) ? StringUtils.EMPTY : token;
    }
}
