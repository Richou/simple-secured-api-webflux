package com.heanoria.reminders.securedapi.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Article {

    private final UUID id;
    private final String title;
}
