package com.heanoria.reminders.securedapi.core.configuration;

import com.heanoria.reminders.securedapi.core.configuration.business.ServicesConfiguration;
import com.heanoria.reminders.securedapi.database.configuration.SecuredApiMongoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecuredApiMongoConfiguration.class, ServicesConfiguration.class})
public class SecuredApiCoreConfiguration {
}
