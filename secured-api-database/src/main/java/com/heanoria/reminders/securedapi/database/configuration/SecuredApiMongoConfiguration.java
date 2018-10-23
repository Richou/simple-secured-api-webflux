package com.heanoria.reminders.securedapi.database.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.heanoria.reminders.securedapi.database.repositories")
public class SecuredApiMongoConfiguration {

}
