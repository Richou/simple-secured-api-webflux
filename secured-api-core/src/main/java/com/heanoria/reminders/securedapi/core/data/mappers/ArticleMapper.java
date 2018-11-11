package com.heanoria.reminders.securedapi.core.data.mappers;

import com.heanoria.reminders.securedapi.common.mappers.SingleMapper;
import com.heanoria.reminders.securedapi.core.data.dto.Article;
import com.heanoria.reminders.securedapi.database.entities.ArticleEntity;

public class ArticleMapper implements SingleMapper<ArticleEntity, Article> {

    @Override
    public Article map(ArticleEntity input) {
        if (input == null) return null;
        return Article.builder().title(input.getTitle()).id(input.getUid()).authorId(input.getAuthorId()).content(input.getContent())
                .createDate(input.getCreateDate()).updateDate(input.getUpdateDate()).build();
    }
}
