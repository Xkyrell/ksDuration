package me.xkyrell.temporal.format;

public interface TemporalFormatter<S> {

    String format(long millis, S style) throws Throwable;

}
