package com.heanoria.reminders.securedapi.core.data.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@JsonDeserialize(builder = User.UserBuilder.class)
public class User {
    private final UUID id;
    private final String username;
    private final String email;
    private final List<Role> authorities;

    @JsonPOJOBuilder(withPrefix = StringUtils.EMPTY)
    public static class UserBuilder {}
}
