package com.heanoria.reminders.securedapi.security.internal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class AuthentHeadersExchangeMatcher implements ServerWebExchangeMatcher {
    @Override
    public Mono<MatchResult> matches(ServerWebExchange serverWebExchange) {
        Mono<ServerHttpRequest> request = Mono.just(serverWebExchange).map(ServerWebExchange::getRequest);

        return request.map(ServerHttpRequest::getHeaders)
            .filter(headers -> headers.containsKey(HttpHeaders.AUTHORIZATION))
            .flatMap(it -> MatchResult.match())
            .switchIfEmpty(MatchResult.notMatch());
    }
}
