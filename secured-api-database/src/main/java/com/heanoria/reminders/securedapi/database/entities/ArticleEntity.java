package com.heanoria.reminders.securedapi.database.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
@Data
@Builder
public class ArticleEntity {
    @Id
    private String id;
    private String title;
    private String content;
}
