package com.heanoria.reminders.securedapi.database.repositories;

import com.heanoria.reminders.securedapi.database.entities.ArticleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * This Non Reactive Repository will only be used in Security Custom Method.
 */
public interface ArticleRepository extends MongoRepository<ArticleEntity, String> {

    ArticleEntity getByUid(@Param("articleId") UUID articleId);
}
