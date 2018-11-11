package com.heanoria.reminders.securedapi.security.proxies;

import com.heanoria.reminders.securedapi.database.entities.ArticleEntity;
import com.heanoria.reminders.securedapi.database.repositories.ArticleRepository;
import com.heanoria.reminders.securedapi.security.internal.UserAuthentication;
import org.springframework.security.core.Authentication;

import java.util.UUID;

// TODO: Security Custom Method does not support reactive Method (Mono, Flux) for now.
// TODO: So a workaround, is to create a non Reactive Repository and use it here
// TODO; The issue is logged here : https://github.com/spring-projects/spring-security/issues/4841
public class ArticleServiceProxy {

    private final ArticleRepository articleRepository;

    public ArticleServiceProxy(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public boolean isUserOwnerOfArticle(Authentication principal, UUID articleId) {
        if (principal instanceof UserAuthentication) {
            ArticleEntity article = this.articleRepository.getByUid(articleId);
            if (article != null) {
                return ((UserAuthentication)principal).getDetails().getId().equals(article.getAuthorId());
            }
        }
        return false;
    }
}
