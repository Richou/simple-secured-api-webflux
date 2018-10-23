package com.heanoria.reminders.securedapi.core.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Article.ArticleBuilder.class)
public class Article {

    private final UUID id;
    private final String title;
    private final String content;
    private final User author;
    private final Date createDate;
    private final Date updateDate;

    @JsonPOJOBuilder(withPrefix = StringUtils.EMPTY)
    public static class ArticleBuilder {}
}
