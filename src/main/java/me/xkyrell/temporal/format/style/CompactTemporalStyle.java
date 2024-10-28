package me.xkyrell.temporal.format.style;

import lombok.NonNull;
import me.xkyrell.temporal.TemporalUnit;
import me.xkyrell.temporal.format.registry.TemporalRegistry;
import java.util.Map;

public interface CompactTemporalStyle extends TemporalStyle {

    Map<String, TemporalUnit> getUnits();

    interface Builder extends StyleBuilder<Builder, CompactTemporalStyle> {

        Builder unit(@NonNull String syntax, @NonNull TemporalUnit unit);

        Builder unit(@NonNull Map<String, TemporalUnit> units);

        default Builder unit(@NonNull TemporalRegistry<String, TemporalUnit> registry) {
            return unit(registry.loadStyles());
        }
    }
}
