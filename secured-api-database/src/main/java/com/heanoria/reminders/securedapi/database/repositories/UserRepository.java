package com.heanoria.reminders.securedapi.database.repositories;

import com.heanoria.reminders.securedapi.database.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {

}
