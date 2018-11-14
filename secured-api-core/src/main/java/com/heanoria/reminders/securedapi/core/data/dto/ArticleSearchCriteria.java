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

    public Integer getPage() {
        if (page == null) return 0;
        return page;
    }

    public Integer getSize() {
        if (size == null) return 20;
        return size;
    }

    @JsonPOJOBuilder(withPrefix = StringUtils.EMPTY)
    public static final class ArticleSearchCriteriaBuilder {}
}
