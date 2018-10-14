package com.heanoria.reminders.securedapi.rest.controllers;

import com.heanoria.reminders.securedapi.rest.dto.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class ArticleController {

    @GetMapping("/articles/{id}")
    public Mono<Article> doGetArticleById(@PathVariable("id") UUID id) {
        return Mono.just(Article.builder().id(id).title("The Article").build());
    }
}
