package me.xkyrell.temporal.format.impl;

import me.xkyrell.temporal.format.TemporalFormatter;
import me.xkyrell.temporal.format.registry.TemporalEntry;
import me.xkyrell.temporal.format.style.TextualTemporalStyle;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class TextualTemporalFormatter implements TemporalFormatter<TextualTemporalStyle> {

    @Override
    public String format(long millis, TextualTemporalStyle style) throws Throwable {
        StringJoiner joiner = new StringJoiner(" ");
        for (TemporalEntry entry : getSortedEntries(style)) {
            long amount = millis / entry.getMillis();
            if (amount > 0L) {
                int index = style.applyPluralForm(amount);
                String unitName = getUnitOrDefault(entry.getUnitNames(), index);
                joiner.add(Long.toString(amount)).add(unitName);
                millis %= entry.getMillis();
            }
        }
        return joiner.toString();
    }

    private List<TemporalEntry> getSortedEntries(TextualTemporalStyle style) {
        return style.getTemporalEntries().values().stream()
                .sorted(Comparator.comparingLong(TemporalEntry::getMillis).reversed())
                .collect(Collectors.toList());
    }

    private String getUnitOrDefault(String[] unitNames, int index) {
        return (unitNames.length != 0 && index >= 0)
                ? (index < unitNames.length ? unitNames[index] : unitNames[0])
                : "?";
    }
}
