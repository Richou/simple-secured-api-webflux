package com.heanoria.reminders.securedapi.core.services;

import com.heanoria.reminders.securedapi.core.data.dto.Article;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleCreate;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleSearchCriteria;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleUpdate;
import com.heanoria.reminders.securedapi.core.data.mappers.ArticleCreateMapper;
import com.heanoria.reminders.securedapi.core.data.mappers.ArticleMapper;
import com.heanoria.reminders.securedapi.core.ports.SecurityPort;
import com.heanoria.reminders.securedapi.database.entities.ArticleEntity;
import com.heanoria.reminders.securedapi.database.repositories.ReactiveArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

public class ArticleServiceImpl implements ArticleService {

    private final static Logger logger = LoggerFactory.getLogger(ArticleService.class);

    private final ReactiveArticleRepository reactiveArticleRepository;
    private final ArticleMapper articleMapper;
    private final SecurityPort securityPort;

    public ArticleServiceImpl(ReactiveArticleRepository reactiveArticleRepository, SecurityPort securityPort) {
        this.reactiveArticleRepository = reactiveArticleRepository;
        this.securityPort = securityPort;
        this.articleMapper = new ArticleMapper();
    }


    @Override
    public Flux<Article> findByCriteria(ArticleSearchCriteria criteria) {
        if (criteria.getTitle() != null) {
            return this.findAllByTitle(criteria.getTitle(), PageRequest.of(criteria.getPage() != null ? criteria.getPage() : 0, criteria.getSize() != null ? criteria.getSize() : 20));
        }
        return this.findAll().skip(criteria.getPage() * criteria.getSize()).take(criteria.getSize());
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

    @Override
    public Mono<Article> updateArticle(ArticleUpdate articleUpdate, UUID id) {
        return this.reactiveArticleRepository.getByUid(id).map(entity -> {
            entity.setContent(articleUpdate.getContent());
            entity.setTitle(articleUpdate.getTitle());
            entity.setUpdateDate(new Date());
            return entity;
        }).flatMap(this.reactiveArticleRepository::save).map(this.articleMapper::map);
    }
}
