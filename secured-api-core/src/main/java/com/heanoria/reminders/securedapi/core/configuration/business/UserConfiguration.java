package com.heanoria.reminders.securedapi.core.configuration.business;

import com.heanoria.reminders.securedapi.core.services.UserService;
import com.heanoria.reminders.securedapi.core.services.UserServiceImpl;
import com.heanoria.reminders.securedapi.database.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }
}
