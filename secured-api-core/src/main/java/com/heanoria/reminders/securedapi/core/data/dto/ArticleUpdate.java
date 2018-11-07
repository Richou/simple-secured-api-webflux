package com.heanoria.reminders.securedapi.core.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = ArticleUpdate.ArticleUpdateBuilder.class)
public class ArticleUpdate {

    private final String title;
    private final String content;

    @JsonPOJOBuilder(withPrefix = StringUtils.EMPTY)
    public static class ArticleUpdateBuilder {}
}
