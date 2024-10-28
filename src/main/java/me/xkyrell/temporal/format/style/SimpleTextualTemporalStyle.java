package me.xkyrell.temporal.format.style;

import lombok.Getter;
import lombok.NonNull;
import me.xkyrell.temporal.format.TemporalFormatter;
import me.xkyrell.temporal.format.TemporalParser;
import me.xkyrell.temporal.format.registry.TemporalEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

final class SimpleTextualTemporalStyle extends AbstractTemporalStyle<TextualTemporalStyle> implements TextualTemporalStyle {

    @Getter
    private final Map<String, TemporalEntry> temporalEntries;
    private final Function<Long, Integer> pluralizer;

    private SimpleTextualTemporalStyle(
            TemporalFormatter<TextualTemporalStyle> formatter,
            TemporalParser<TextualTemporalStyle> parser,
            Map<String, TemporalEntry> temporalEntries,
            Function<Long, Integer> pluralizer
    ) {
        super(formatter, parser);

        this.temporalEntries = temporalEntries;
        this.pluralizer = pluralizer;
    }

    @Override
    public int applyPluralForm(long millis) {
        return pluralizer.apply(millis);
    }

    static final class SimpleBuilder extends AbstractBuilder<Builder, TextualTemporalStyle> implements Builder {

        private final Map<String, TemporalEntry> temporalEntries = new HashMap<>();
        private Function<Long, Integer> pluralizer;

        @Override
        public Builder pluralize(@NonNull Function<Long, Integer> pluralizer) {
            this.pluralizer = pluralizer;
            return self;
        }

        @Override
        public Builder unit(@NonNull Map<String, TemporalEntry> entries) {
            entries.forEach(this::unit);
            return self;
        }

        @Override
        public Builder unit(@NonNull String syntax, @NonNull TemporalEntry entry) {
            temporalEntries.putIfAbsent(syntax, entry);
            return self;
        }

        @Override
        public TextualTemporalStyle build() {
            return new SimpleTextualTemporalStyle(
                    getFormatter(), getParser(), temporalEntries, pluralizer
            );
        }
    }
}
