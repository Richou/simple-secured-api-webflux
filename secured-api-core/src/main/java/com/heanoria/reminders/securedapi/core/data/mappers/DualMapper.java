package com.heanoria.reminders.securedapi.core.data.mappers;

import java.util.List;
import java.util.stream.Collectors;

public interface DualMapper<T, K> {

    K map(T input);

    T invertMap(K input);

    default List<K> mapList(List<T> inputs) {
        if (inputs == null) return null;
        return inputs.stream().map(this::map).collect(Collectors.toList());
    }

    default List<T> invertMapList(List<K> inputs) {
        if (inputs == null) return null;
        return inputs.stream().map(this::invertMap).collect(Collectors.toList());
    }
}
