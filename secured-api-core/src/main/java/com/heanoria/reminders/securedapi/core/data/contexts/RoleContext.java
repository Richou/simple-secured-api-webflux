package com.heanoria.reminders.securedapi.core.data.contexts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleContext {

    private final String authority;
}
