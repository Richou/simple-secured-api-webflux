package com.heanoria.reminders.securedapi.core.configuration.business;

import com.heanoria.reminders.securedapi.core.ports.SecurityPort;
import com.heanoria.reminders.securedapi.core.services.ArticleService;
import com.heanoria.reminders.securedapi.core.services.ArticleServiceImpl;
import com.heanoria.reminders.securedapi.core.services.UserService;
import com.heanoria.reminders.securedapi.core.services.UserServiceImpl;
import com.heanoria.reminders.securedapi.database.repositories.ReactiveArticleRepository;
import com.heanoria.reminders.securedapi.database.repositories.ReactiveUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Bean
    public UserService userService(ReactiveUserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public ArticleService articleService(ReactiveArticleRepository reactiveArticleRepository, SecurityPort securityPort) {
        return new ArticleServiceImpl(reactiveArticleRepository, securityPort);
    }
}
