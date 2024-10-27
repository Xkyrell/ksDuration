package me.xkyrell.temporal;

import lombok.NonNull;

public interface TemporalValue extends Comparable<TemporalValue> {

    long get(@NonNull TemporalUnit unit);

    long getMillis();

    default boolean isGreaterThan(@NonNull TemporalValue value) {
        return compareTo(value) > 0;
    }

    default boolean isLessThan(@NonNull TemporalValue value) {
        return compareTo(value) < 0;
    }

    default boolean isEqual(@NonNull TemporalValue value) {
        return compareTo(value) == 0;
    }
}
