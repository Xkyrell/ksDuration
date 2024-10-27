package me.xkyrell.temporal.format.style;

import me.xkyrell.temporal.TemporalUnit;
import me.xkyrell.temporal.format.registry.TemporalRegistry;
import java.util.Map;

public interface CompactTemporalStyle extends TemporalStyle {

    Map<String, TemporalUnit> getUnits();

    interface Builder extends StyleBuilder<Builder, CompactTemporalStyle> {

        Builder unit(String syntax, TemporalUnit unit);

        Builder unit(Map<String, TemporalUnit> entries);

        default Builder unit(TemporalRegistry<String, TemporalUnit> registry) {
            return unit(registry.loadStyles());
        }
    }
}
