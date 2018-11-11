package com.heanoria.reminders.securedapi.core.data.mappers;

import com.heanoria.reminders.securedapi.common.mappers.SingleMapper;
import com.heanoria.reminders.securedapi.core.data.dto.ArticleCreate;
import com.heanoria.reminders.securedapi.database.entities.ArticleEntity;

import java.util.Date;
import java.util.UUID;

public class ArticleCreateMapper implements SingleMapper<ArticleCreate, ArticleEntity> {

    private final UUID authorId;

    public ArticleCreateMapper(UUID authorId) {
        this.authorId = authorId;
    }

    @Override
    public ArticleEntity map(ArticleCreate input) {
        if (input == null) return null;
        return ArticleEntity.builder().uid(UUID.randomUUID()).content(input.getContent()).title(input.getTitle()).authorId(authorId).createDate(new Date()).updateDate(new Date()).build();
    }
}
