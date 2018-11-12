package com.heanoria.reminders.securedapi.core.data.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@AllArgsConstructor
@JsonDeserialize(builder = ArticleSearchCriteria.ArticleSearchCriteriaBuilder.class)
public class ArticleSearchCriteria {

    private final String title;
    private final Integer page;
    private final Integer size;

    @JsonPOJOBuilder(withPrefix = StringUtils.EMPTY)
    public static final class ArticleSearchCriteriaBuilder {}
}
