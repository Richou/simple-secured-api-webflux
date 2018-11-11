package com.heanoria.reminders.securedapi.database.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories("com.heanoria.reminders.securedapi.database.repositories")
@EnableMongoRepositories("com.heanoria.reminders.securedapi.database.repositories")
public class SecuredApiMongoConfiguration {

}
