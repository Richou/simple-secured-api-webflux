package com.heanoria.reminders.securedapi.core.data.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@JsonDeserialize(builder = Credentials.CredentialsBuilder.class)
public class Credentials {
    private final String email;
    private final String password;

    @JsonPOJOBuilder(withPrefix = StringUtils.EMPTY)
    public final static class CredentialsBuilder {}
}
