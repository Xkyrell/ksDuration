package me.xkyrell.temporal;

import lombok.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Temporal implements TemporalHolder<Temporal>, TemporalValue<Temporal>, Cloneable {

    public static final Temporal ZERO = new Temporal(0L);

    private long millis;

    public static Temporal of(long millis) {
        return new Temporal(millis);
    }

    public static Temporal of(long millis, @NonNull TimeUnit unit) {
        return new Temporal(unit.toMillis(millis));
    }

    public static Temporal of(@NonNull Duration duration) {
        return new Temporal(duration.toMillis());
    }

    public static Temporal of(@NonNull Temporal temporal) {
        return new Temporal(temporal.millis);
    }

    @Override
    public Temporal operation(@NonNull Temporal temporal, BiFunction<Long, Long, Long> operator) {
        millis = operator.apply(millis, temporal.millis);
        return this;
    }

    @Override
    public long get(@NonNull TimeUnit unit) {
        return unit.convert(millis, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NonNull Temporal other) {
        return Long.compare(millis, other.millis);
    }

    @SneakyThrows(CloneNotSupportedException.class)
    @Override
    public Temporal clone() {
        return (Temporal) super.clone();
    }

    public Duration toDuration() {
        return Duration.ofMillis(millis);
    }

    public boolean isZero() {
        return (millis == 0L);
    }
}
