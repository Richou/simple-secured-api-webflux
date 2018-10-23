package com.heanoria.reminders.securedapi.database.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Document(collection = "articles")
public class ArticleEntity {
    @Id
    private String id;
    private UUID uid;
    private String title;
    private String content;
    private UserEntity author;
    private Date createDate;
    private Date updateDate;
}
