package com.heanoria.reminders.securedapi.rest.configurations;

import com.heanoria.reminders.securedapi.core.configuration.SecuredApiCoreConfiguration;
import com.heanoria.reminders.securedapi.security.annotations.EnableSecuredApiSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableSecuredApiSecurity
@Import({SecuredApiCoreConfiguration.class})
public class SecuredApiConfiguration {
}
