package com.heanoria.reminders.securedapi.security.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {

    private final String token;
}
