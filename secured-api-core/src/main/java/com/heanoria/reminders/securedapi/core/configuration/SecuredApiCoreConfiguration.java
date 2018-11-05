package com.heanoria.reminders.securedapi.core.configuration;

import com.heanoria.reminders.securedapi.core.configuration.business.UserConfiguration;
import com.heanoria.reminders.securedapi.database.configuration.SecuredApiMongoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecuredApiMongoConfiguration.class, UserConfiguration.class})
public class SecuredApiCoreConfiguration {
}
