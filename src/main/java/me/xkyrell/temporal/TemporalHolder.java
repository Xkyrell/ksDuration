package me.xkyrell.temporal;

import lombok.NonNull;
import java.util.function.BiFunction;

public interface TemporalHolder<T extends TemporalHolder<T>> {

    T operation(@NonNull T temporal, BiFunction<Long, Long, Long> operator);

    default T plus(@NonNull T temporal) {
        return operation(temporal, Long::sum);
    }

    default T minus(@NonNull T temporal) {
        return operation(temporal, (a, b) -> a - b);
    }

    default T multiply(int multiplier) {
        return operation((T) this, (a, __) -> a * multiplier);
    }

    default T divide(int divisor) {
        return operation((T) this, (a, __) -> a / divisor);
    }
}
