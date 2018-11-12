package com.heanoria.reminders.securedapi.core.services;

import com.heanoria.reminders.securedapi.core.data.dto.Article;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleCreate;
import com.heanoria.reminders.securedapi.core.data.mappers.ArticleCreateMapper;
import com.heanoria.reminders.securedapi.core.data.mappers.ArticleMapper;
import com.heanoria.reminders.securedapi.core.ports.SecurityPort;
import com.heanoria.reminders.securedapi.database.entities.ArticleEntity;
import com.heanoria.reminders.securedapi.database.repositories.ReactiveArticleRepository;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class ArticleServiceImpl implements ArticleService {

    private final ReactiveArticleRepository reactiveArticleRepository;
    private final ArticleMapper articleMapper;
    private final SecurityPort securityPort;

    public ArticleServiceImpl(ReactiveArticleRepository reactiveArticleRepository, SecurityPort securityPort) {
        this.reactiveArticleRepository = reactiveArticleRepository;
        this.securityPort = securityPort;
        this.articleMapper = new ArticleMapper();
    }

    @Override
    public Flux<Article> findAll() {
        return this.reactiveArticleRepository.findAll().map(this.articleMapper::map);
    }

    @Override
    public Flux<Article> findAllByTitle(String title, Pageable pageable) {
        return this.reactiveArticleRepository.findByTitleLike(title, pageable).map(this.articleMapper::map);
    }

    @Override
    public Mono<Article> getArticleById(UUID articleId) {
        return this.reactiveArticleRepository.getByUid(articleId).map(this.articleMapper::map);
    }

    @Override
    public Mono<Article> createArticle(ArticleCreate article, String token) {
        UUID connectedUserId = securityPort.getAuthenticatedUserId(token);
        ArticleEntity entity = new ArticleCreateMapper(connectedUserId).map(article);
        return this.reactiveArticleRepository.save(entity).map(this.articleMapper::map);
    }
}
