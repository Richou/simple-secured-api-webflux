package com.heanoria.reminders.securedapi.core.data.mappers;

import com.heanoria.reminders.securedapi.common.mappers.SingleMapper;
import com.heanoria.reminders.securedapi.core.data.contexts.RoleContext;
import com.heanoria.reminders.securedapi.database.entities.RoleEntity;

public class RoleContextMapper implements SingleMapper<RoleEntity, RoleContext> {
    @Override
    public RoleContext map(RoleEntity input) {
        if (input == null) return null;
        return RoleContext.builder().authority(input.getAuthority()).build();
    }
}
