package com.heanoria.reminders.securedapi.database.repositories;

import com.heanoria.reminders.securedapi.database.entities.ArticleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<ArticleEntity, String> {

}
