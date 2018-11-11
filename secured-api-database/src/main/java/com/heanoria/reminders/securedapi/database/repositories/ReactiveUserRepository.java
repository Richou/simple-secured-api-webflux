package com.heanoria.reminders.securedapi.database.repositories;

import com.heanoria.reminders.securedapi.database.entities.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReactiveUserRepository extends ReactiveMongoRepository<UserEntity, String> {

    Mono<UserEntity> findByEmail(@Param("email") String email);
    Mono<UserEntity> findByUid(@Param("uid") UUID uid);
}
