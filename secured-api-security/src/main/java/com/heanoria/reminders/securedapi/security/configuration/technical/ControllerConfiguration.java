package com.heanoria.reminders.securedapi.security.configuration.technical;

import com.heanoria.reminders.securedapi.security.controllers.LoginController;
import com.heanoria.reminders.securedapi.security.internal.AuthentManager;
import com.heanoria.reminders.securedapi.security.internal.TokenHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {

    @Bean
    public LoginController loginController(TokenHandler tokenHandler, AuthentManager authentManager) {
        return new LoginController(tokenHandler, authentManager);
    }
}
