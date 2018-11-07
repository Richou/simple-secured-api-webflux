package com.heanoria.reminders.securedapi.core.services;

import com.heanoria.reminders.securedapi.database.repositories.ArticleRepository;

public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
}
