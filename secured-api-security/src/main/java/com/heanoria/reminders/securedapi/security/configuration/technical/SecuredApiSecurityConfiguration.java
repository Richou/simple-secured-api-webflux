package com.heanoria.reminders.securedapi.security.configuration.technical;

import com.heanoria.reminders.securedapi.security.configuration.properties.KeyPairProperties;
import com.heanoria.reminders.securedapi.security.internal.*;
import com.heanoria.reminders.securedapi.security.proxies.UserServiceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
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
@Import({KeyPairProperties.class, ProxiesConfiguration.class, TokenConfiguration.class, ControllerConfiguration.class})
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecuredApiSecurityConfiguration {

    private final UserServiceProxy userServiceProxy;
    private final TokenHandler tokenHandler;

    private static final String[] AUTH_WHITELIST = {
            "/webjars/**",
            "/favicon.ico",
            "/swagger-ui.html**",
            "/v1/login",
            "/swagger-resources/**",
            "/v2/api-docs",
    };

    public SecuredApiSecurityConfiguration(UserServiceProxy userServiceProxy, TokenHandler tokenHandler) {
        this.userServiceProxy = userServiceProxy;
        this.tokenHandler = tokenHandler;
    }

    @Bean
    public UnauthorizedAuthenticationEntryPoint unauthorizedAuthenticationEntryPoint() {
        return new UnauthorizedAuthenticationEntryPoint();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, AuthenticationWebFilter webFilter, UnauthorizedAuthenticationEntryPoint entryPoint) {
        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable();
        http
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .authorizeExchange()
                .and()
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .and()
                .authorizeExchange()
                .and()
                .addFilterAt(webFilter, SecurityWebFiltersOrder.AUTHORIZATION)
                .authorizeExchange()
                .pathMatchers(AUTH_WHITELIST).permitAll()
                .anyExchange().authenticated();

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
