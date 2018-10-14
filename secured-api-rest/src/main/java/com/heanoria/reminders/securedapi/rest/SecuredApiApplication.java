package com.heanoria.reminders.securedapi.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecuredApiApplication {

    private final static Logger logger = LoggerFactory.getLogger(SecuredApiApplication.class);

    public static void main(String ... args) {
        SpringApplication.run(SecuredApiApplication.class, args);
        logger.info("=======================================================");
        logger.info("==         Secured Api Application Started");
        logger.info("=======================================================");
    }
}
