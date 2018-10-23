package com.heanoria.reminders.securedapi.database.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleEntity {

    private String authority;
}
