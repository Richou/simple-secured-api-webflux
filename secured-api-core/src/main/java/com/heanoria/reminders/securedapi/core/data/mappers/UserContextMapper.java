package com.heanoria.reminders.securedapi.core.data.mappers;

import com.heanoria.reminders.securedapi.common.mappers.SingleMapper;
import com.heanoria.reminders.securedapi.core.data.contexts.RoleContext;
import com.heanoria.reminders.securedapi.core.data.contexts.UserContext;
import com.heanoria.reminders.securedapi.database.entities.UserEntity;

import java.util.Collections;
import java.util.stream.Collectors;

public class UserContextMapper implements SingleMapper<UserEntity, UserContext> {

    private final RoleContextMapper roleContextMapper;

    public UserContextMapper() {
        this.roleContextMapper = new RoleContextMapper();
    }

    @Override
    public UserContext map(UserEntity input) {
        if (input == null) return null;
        return UserContext.builder().id(input.getUid()).authorities((input.getAuthorities() != null) ? input.getAuthorities().stream().map(it -> RoleContext.builder().authority(it).build()).collect(Collectors.toList()): Collections.emptyList()).email(input.getEmail()).password(input.getPassword())
                .username(input.getUsername()).build();
    }
}
