package me.xkyrell.temporal;

import lombok.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Temporal implements TemporalHolder<Temporal>, TemporalValue, Cloneable {

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

    public static Temporal of(@NonNull TemporalValue value) {
        return new Temporal(value.getMillis());
    }

    public static Temporal between(long start, long end) {
        return new Temporal(end - start);
    }

    public static Temporal between(@NonNull TemporalValue start, @NonNull TemporalValue end) {
        return between(start.getMillis(), end.getMillis());
    }

    @Override
    public Temporal operation(@NonNull Function<Long, Long> operator) {
        millis = operator.apply(millis);
        return this;
    }

    @Override
    public long get(@NonNull TimeUnit unit) {
        return unit.convert(millis, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NonNull TemporalValue other) {
        return Long.compare(millis, other.getMillis());
    }

    @SneakyThrows(CloneNotSupportedException.class)
    @Override
    public Temporal clone() {
        return (Temporal) super.clone();
    }

    public Duration toDuration() {
        return Duration.ofMillis(millis);
    }

    public boolean isValid() {
        return (millis > 0);
    }

    public boolean isZero() {
        return (millis == 0L);
    }
}
