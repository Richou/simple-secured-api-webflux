package com.heanoria.reminders.securedapi.security.configuration.technical;

import com.heanoria.reminders.securedapi.security.configuration.properties.KeyPairProperties;
import com.heanoria.reminders.securedapi.security.internal.*;
import com.heanoria.reminders.securedapi.security.proxies.UserServiceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@ConfigurationProperties
@Import({KeyPairProperties.class, ProxiesConfiguration.class, TokenConfiguration.class})
@EnableWebFluxSecurity
public class SecuredApiSecurityConfiguration {

    private final UserServiceProxy userServiceProxy;
    private final TokenHandler tokenHandler;

    private static final String[] AUTH_WHITELIST = {
            "/favicon.ico",
    };

    public SecuredApiSecurityConfiguration(UserServiceProxy userServiceProxy, TokenHandler tokenHandler) {
        this.userServiceProxy = userServiceProxy;
        this.tokenHandler = tokenHandler;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, AuthenticationWebFilter webFilter) {
        // Disable login form
        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable();
        // Add a filter with Authorization on header   http.addFilterAt(webFilter(),SecurityWebFiltersOrder.AUTHORIZATION)
        http.authorizeExchange()
                // Passing a white list endpoint, do not need to
                // authenticate
                .pathMatchers(AUTH_WHITELIST).permitAll()
                .anyExchange().authenticated()
                .and().addFilterAt(webFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }

    @Bean
    public AuthentManager authentManager() {
        return new AuthentManager(userServiceProxy, passwordEncoder());
    }

    @Bean
    public AuthenticationWebFilter webFilter(AuthentManager authentManager) {
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(authentManager);
        authenticationWebFilter.setAuthenticationConverter(new TokenAuthenticationConverter(tokenHandler));
        authenticationWebFilter.setRequiresAuthenticationMatcher(new AuthentHeadersExchangeMatcher());
        authenticationWebFilter.setSecurityContextRepository(new WebSessionServerSecurityContextRepository());
        return authenticationWebFilter;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
