package com.heanoria.reminders.securedapi.database.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private UUID uid;
    private String username;
    private String password;
    private String email;
    private Set<ArticleEntity> articles;
    private Set<RoleEntity> authorities;
}
