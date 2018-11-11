package com.heanoria.reminders.securedapi.core.data.mappers;

import com.heanoria.reminders.securedapi.common.mappers.SingleMapper;
import com.heanoria.reminders.securedapi.core.data.contexts.UserContext;
import com.heanoria.reminders.securedapi.database.entities.UserEntity;

import java.util.ArrayList;

public class UserContextMapper implements SingleMapper<UserEntity, UserContext> {

    @Override
    public UserContext map(UserEntity input) {
        if (input == null) return null;
        return UserContext.builder().id(input.getUid()).authorities(new ArrayList<>(input.getAuthorities())).email(input.getEmail()).password(input.getPassword())
                .username(input.getUsername()).build();
    }
}
