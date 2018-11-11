package com.heanoria.reminders.securedapi.core.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Author.AuthorBuilder.class)
public class Author {
    private final UUID id;
    private final String username;
    private final String email;

    @JsonPOJOBuilder(withPrefix = StringUtils.EMPTY)
    public static class AuthorBuilder {}
}
