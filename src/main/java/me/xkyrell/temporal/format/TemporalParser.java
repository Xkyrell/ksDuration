package me.xkyrell.temporal.format;

import me.xkyrell.temporal.format.style.TemporalSchema;

public interface TemporalParser<S extends TemporalSchema> {

    long parse(String value, S style) throws NumberFormatException;

}
