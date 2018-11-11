package com.heanoria.reminders.securedapi.core.services;

import com.heanoria.reminders.securedapi.core.data.dto.Article;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleCreate;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ArticleService {

    Mono<Article> getArticleById(UUID articleId);
    Mono<Article> createArticle(ArticleCreate article, String token);
}
