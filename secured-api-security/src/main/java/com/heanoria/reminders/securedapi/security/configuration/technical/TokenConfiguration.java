package com.heanoria.reminders.securedapi.security.configuration.technical;

import com.heanoria.reminders.securedapi.security.configuration.properties.KeyPairProperties;
import com.heanoria.reminders.securedapi.security.internal.KeyPairHandler;
import com.heanoria.reminders.securedapi.security.internal.TokenHandler;
import com.heanoria.reminders.securedapi.security.proxies.UserServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class TokenConfiguration {

    @Bean
    public KeyPairHandler keyPairHandler(KeyPairProperties jwtProperties) {
        return new KeyPairHandler(jwtProperties);
    }

    @Bean
    public KeyPair keyPair(KeyPairHandler keyPairHandler) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        return keyPairHandler.loadKeyPair();
    }

    @Bean
    public TokenHandler tokenHandler(KeyPair keyPair, UserServiceProxy userServiceProxy) {
        return new TokenHandler(keyPair, userServiceProxy);
    }
}
