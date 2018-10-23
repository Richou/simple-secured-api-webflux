package com.heanoria.reminders.securedapi.core.data.mappers;

import java.util.List;
import java.util.stream.Collectors;

public interface SingleMapper<T, K> {

    K map(T input);

    default List<K> mapList(List<T> inputs) {
        if (inputs == null) return null;
    return inputs.stream().map(this::map).collect(Collectors.toList());
    }
}
