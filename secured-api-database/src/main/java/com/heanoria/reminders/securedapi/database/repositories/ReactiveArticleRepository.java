package com.heanoria.reminders.securedapi.database.repositories;

import com.heanoria.reminders.securedapi.database.entities.ArticleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReactiveArticleRepository extends ReactiveMongoRepository<ArticleEntity, String> {
    Mono<ArticleEntity> getByUid(@Param("articleId") UUID articleId);
}
