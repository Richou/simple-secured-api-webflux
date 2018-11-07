package com.heanoria.reminders.securedapi.rest.controllers;

import com.heanoria.reminders.securedapi.core.data.dto.Article;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleUpdate;
import com.heanoria.reminders.securedapi.security.proxies.ArticleServiceProxy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class ArticleController {

    private final ArticleServiceProxy articleServiceProxy;

    public ArticleController(ArticleServiceProxy articleServiceProxy) {
        this.articleServiceProxy = articleServiceProxy;
    }

    @GetMapping("/articles")
    public Flux<Article> doGetArticles(@RequestHeader("Authorization") String authorization) {
        return Flux.just(Article.builder().id(UUID.randomUUID()).title("The First Article").build(), Article.builder().id(UUID.randomUUID()).title("The Second Article").build());
    }

    @GetMapping("/articles/{id}")
    public Mono<Article> doGetArticleById(@PathVariable("id") UUID id) {
        return Mono.just(Article.builder().id(id).title("The Article").build());
    }

    @PutMapping("/articles/{id}")
    @PreAuthorize("@articleServiceProxy.isUserOwnerOfArticle(authentication, #id) or hasRole('ROLE_ADMIN')")
    public Mono<Article> doPutArticleById(@PathVariable("id") UUID id, @RequestBody ArticleUpdate articleUpdate) {
        return Mono.just(Article.builder().id(id).title("The Article").build());
    }
}
