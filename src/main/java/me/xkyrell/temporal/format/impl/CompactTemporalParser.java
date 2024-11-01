package me.xkyrell.temporal.format.impl;

import lombok.RequiredArgsConstructor;
import me.xkyrell.temporal.TemporalUnit;
import me.xkyrell.temporal.format.TemporalParser;
import me.xkyrell.temporal.format.style.CompactTemporalStyle;

@RequiredArgsConstructor
public class CompactTemporalParser implements TemporalParser<CompactTemporalStyle> {

    private final String pattern;

    @Override
    public long parse(String value, CompactTemporalStyle style) throws NumberFormatException {
        String[] timeParts = value.split(":");
        String[] unitPatterns = pattern.split(":");

        long totalMillis = 0L;
        int length = timeParts.length;
        for (int i = 0; i < length; i++) {
            TemporalUnit unit = style.getUnits().get(unitPatterns[unitPatterns.length - length + i]);
            if (unit != null) {
                totalMillis += Long.parseLong(timeParts[i]) * unit.toMillis();
            }
        }
        return totalMillis;
    }
}
