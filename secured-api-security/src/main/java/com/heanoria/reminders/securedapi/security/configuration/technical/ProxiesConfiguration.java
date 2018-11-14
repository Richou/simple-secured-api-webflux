package com.heanoria.reminders.securedapi.security.configuration.technical;

import com.heanoria.reminders.securedapi.core.services.UserService;
import com.heanoria.reminders.securedapi.database.repositories.ArticleRepository;
import com.heanoria.reminders.securedapi.security.proxies.AnonymousServiceProxy;
import com.heanoria.reminders.securedapi.security.proxies.ArticleServiceProxy;
import com.heanoria.reminders.securedapi.security.proxies.UserServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxiesConfiguration {

    @Bean
    public UserServiceProxy userServiceProxy(UserService userService) {
        return new UserServiceProxy(userService);
    }

    @Bean
    public ArticleServiceProxy articleServiceProxy(ArticleRepository articleRepository) {
        return new ArticleServiceProxy(articleRepository);
    }

    @Bean
    public AnonymousServiceProxy anonymousServiceProxy() {
        return new AnonymousServiceProxy();
    }

}
