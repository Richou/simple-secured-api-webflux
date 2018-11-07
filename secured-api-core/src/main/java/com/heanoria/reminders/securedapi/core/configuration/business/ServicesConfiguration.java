package com.heanoria.reminders.securedapi.core.configuration.business;

import com.heanoria.reminders.securedapi.core.services.ArticleService;
import com.heanoria.reminders.securedapi.core.services.ArticleServiceImpl;
import com.heanoria.reminders.securedapi.core.services.UserService;
import com.heanoria.reminders.securedapi.core.services.UserServiceImpl;
import com.heanoria.reminders.securedapi.database.repositories.ArticleRepository;
import com.heanoria.reminders.securedapi.database.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public ArticleService articleService(ArticleRepository articleRepository) {
        return new ArticleServiceImpl(articleRepository);
    }
}
