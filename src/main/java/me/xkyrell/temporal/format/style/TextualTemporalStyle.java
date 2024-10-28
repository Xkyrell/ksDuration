package me.xkyrell.temporal.format.style;

import lombok.NonNull;
import me.xkyrell.temporal.format.registry.TemporalEntry;
import me.xkyrell.temporal.format.registry.TemporalRegistry;
import java.util.Map;
import java.util.function.Function;

public interface TextualTemporalStyle extends TemporalStyle {

    int applyPluralForm(long millis);

    Map<String, TemporalEntry> getTemporalEntries();

    interface Builder extends StyleBuilder<Builder, TextualTemporalStyle> {

        Builder pluralize(@NonNull Function<Long, Integer> pluralizer);

        Builder unit(@NonNull String syntax, @NonNull TemporalEntry entry);

        Builder unit(@NonNull Map<String, TemporalEntry> entries);

        default Builder unit(@NonNull TemporalRegistry<String, TemporalEntry> registry) {
            return unit(registry.loadStyles());
        }
    }
}
