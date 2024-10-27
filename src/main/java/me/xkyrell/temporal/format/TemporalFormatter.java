package me.xkyrell.temporal.format;

import me.xkyrell.temporal.format.style.TemporalSchema;

public interface TemporalFormatter<S extends TemporalSchema> {

    String format(long millis, S style) throws Throwable;

}
