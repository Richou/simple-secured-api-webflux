package com.heanoria.reminders.securedapi.security.annotations;

import com.heanoria.reminders.securedapi.security.configuration.technical.SecuredApiSecurityConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({SecuredApiSecurityConfiguration.class})
public @interface EnableSecuredApiSecurity {
}
