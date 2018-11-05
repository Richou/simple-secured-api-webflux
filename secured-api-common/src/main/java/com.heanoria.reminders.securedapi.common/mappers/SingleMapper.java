package com.heanoria.reminders.securedapi.common.mappers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface SingleMapper<T, K> {

    K map(T input);

    default List<K> mapList(Collection<T> inputs) {
        if (inputs == null) return null;
        return inputs.stream().map(this::map).collect(Collectors.toList());
    }
}
