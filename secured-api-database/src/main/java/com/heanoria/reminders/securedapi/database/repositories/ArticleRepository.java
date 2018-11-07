package com.heanoria.reminders.securedapi.database.repositories;

import com.heanoria.reminders.securedapi.database.entities.ArticleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ArticleRepository extends ReactiveMongoRepository<ArticleEntity, String> {
    Mono<ArticleEntity> getByUidAndAuthorUid(@Param("articleId") UUID articleId, @Param("authorId") UUID authorId);
}
