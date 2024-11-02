package me.xkyrell.temporal.format.style;

import lombok.Getter;
import lombok.NonNull;
import me.xkyrell.temporal.TemporalUnit;
import me.xkyrell.temporal.format.TemporalFormatter;
import me.xkyrell.temporal.format.TemporalParser;
import java.util.HashMap;
import java.util.Map;

@Getter
final class SimpleCompactTemporalStyle extends AbstractTemporalStyle<CompactTemporalStyle> implements CompactTemporalStyle {

    private final Map<String, TemporalUnit> units;

    private SimpleCompactTemporalStyle(
            TemporalFormatter<CompactTemporalStyle> formatter,
            TemporalParser<CompactTemporalStyle> parser,
            Map<String, TemporalUnit> units
    ) {
        super(formatter, parser);

        this.units = units;
    }

    static final class SimpleBuilder extends AbstractBuilder<Builder, CompactTemporalStyle> implements Builder {

        private final Map<String, TemporalUnit> units = new HashMap<>();

        @Override
        public Builder unit(@NonNull String syntax, @NonNull TemporalUnit unit) {
            units.putIfAbsent(syntax, unit);
            return self;
        }

        @Override
        public Builder unit(@NonNull Map<String, TemporalUnit> units) {
            units.forEach(this::unit);
            return self;
        }

        @Override
        public CompactTemporalStyle build() {
            return new SimpleCompactTemporalStyle(
                    getFormatter(), getParser(), units
            );
        }
    }
}
