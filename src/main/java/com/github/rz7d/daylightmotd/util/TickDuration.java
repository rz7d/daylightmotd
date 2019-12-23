package com.github.rz7d.daylightmotd.util;

import java.util.Objects;

public final class TickDuration {

    public static TickDuration between(long fromIncluded, long toExcluded) {
        return new TickDuration(fromIncluded, toExcluded);
    }

    private final long fromIncluded;
    private final long toExcluded;

    private TickDuration(long fromExcluded, long toExcluded) {
        this.fromIncluded = fromExcluded % 24000;
        this.toExcluded = toExcluded % 24000;
    }

    public boolean test(long time) {
        final long normalized = time % 24000;
        return (normalized >= fromIncluded) && (normalized < toExcluded);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TickDuration duration = (TickDuration) o;
        return fromIncluded == duration.fromIncluded &&
            toExcluded == duration.toExcluded;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromIncluded, toExcluded);
    }

}
