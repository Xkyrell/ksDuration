package me.xkyrell.temporal.format;

public interface TemporalParser<S> {

    long parse(String value, S style) throws NumberFormatException;

}
