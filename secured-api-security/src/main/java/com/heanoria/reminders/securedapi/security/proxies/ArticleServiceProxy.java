package com.heanoria.reminders.securedapi.security.proxies;

import com.heanoria.reminders.securedapi.core.services.ArticleService;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public class ArticleServiceProxy {

    private final ArticleService articleService;

    public ArticleServiceProxy(ArticleService articleService) {
        this.articleService = articleService;
    }

    public boolean isUserOwnerOfArticle(Authentication principal, UUID articleId) {
        return true;
    }
}
