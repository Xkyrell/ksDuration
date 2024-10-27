package me.xkyrell.temporal.format.style;

import me.xkyrell.temporal.format.registry.TemporalEntry;
import me.xkyrell.temporal.format.registry.TemporalRegistry;
import java.util.Map;
import java.util.function.Function;

public interface TextualTemporalStyle extends TemporalStyle {

    int applyPluralForm(long millis);

    Map<String, TemporalEntry> getTemporalEntries();

    interface Builder extends StyleBuilder<Builder, TextualTemporalStyle> {

        Builder pluralize(Function<Long, Integer> pluralizer);

        Builder unit(String syntax, TemporalEntry entry);

        Builder unit(Map<String, TemporalEntry> entries);

        default Builder unit(TemporalRegistry<String, TemporalEntry> registry) {
            return unit(registry.loadStyles());
        }
    }
}
