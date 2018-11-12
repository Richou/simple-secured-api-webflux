package com.heanoria.reminders.securedapi.core.services;

import com.heanoria.reminders.securedapi.core.data.dto.Article;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleCreate;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ArticleService {

    Flux<Article> findAll();
    Flux<Article> findAllByTitle(String title, Pageable pageable);
    Mono<Article> getArticleById(UUID articleId);
    Mono<Article> createArticle(ArticleCreate article, String token);
}
