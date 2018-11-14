package com.heanoria.reminders.securedapi.rest.controllers;

import com.heanoria.reminders.securedapi.core.data.dto.Article;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleCreate;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleSearchCriteria;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleUpdate;
import com.heanoria.reminders.securedapi.core.services.ArticleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public Flux<Article> doGetArticles(@RequestHeader("Authorization") String authorization, ArticleSearchCriteria criteria) {
        return this.articleService.findByCriteria(criteria);
    }

    @GetMapping("/articles/{id}")
    public Mono<Article> doGetArticleById(@PathVariable("id") UUID id) {
        return this.articleService.getArticleById(id);
    }

    @PostMapping("/articles")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Mono<Article> doCreateArticle(@RequestHeader("Authorization") String authorization, @RequestBody ArticleCreate article) {
        return this.articleService.createArticle(article, authorization);
    }

    @PutMapping("/articles/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @articleServiceProxy.isUserOwnerOfArticle(authentication, #id)")
    public Mono<Article> doPutArticleById(@RequestHeader("Authorization") String authorization, @PathVariable("id") UUID id, @RequestBody ArticleUpdate articleUpdate) {
        return this.articleService.updateArticle(articleUpdate, id);
    }
}
