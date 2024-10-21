package me.xkyrell.temporal;

import lombok.NonNull;

import java.util.concurrent.TimeUnit;

public interface TemporalValue<V extends TemporalValue<V>> extends Comparable<V> {

    long get(@NonNull TimeUnit unit);

    long getMillis();

    default boolean isGreaterThan(@NonNull V value) {
        return compareTo(value) > 0;
    }

    default boolean isLessThan(@NonNull V value) {
        return compareTo(value) < 0;
    }

    default boolean isEqual(@NonNull V value) {
        return compareTo(value) == 0;
    }
}
