package com.github.rz7d.daylightmotd.util;

import com.google.common.base.Preconditions;

import java.util.Objects;

public final class TickDuration {

    public static TickDuration between(long fromIncluded, long toExcluded) {
        Preconditions.checkState(fromIncluded >= 0, "fromIncluded must be positive");
        Preconditions.checkState(toExcluded >= 0, "toExcluded must be positive");
        Preconditions.checkState(fromIncluded <= toExcluded, "toExcluded must be greater than or equal to fromIncluded");
        return new TickDuration(fromIncluded, toExcluded);
    }

    private final long fromIncluded;
    private final long toExcluded;

    private TickDuration(long fromExcluded, long toExcluded) {
        this.fromIncluded = fromExcluded % 24000;
        this.toExcluded = toExcluded % 24000;
    }

    public boolean test(long time) {
        Preconditions.checkState(time >= 0, "time must be positive");
        final long normalized = time % 24000;
        return normalized >= fromIncluded && (normalized < toExcluded || toExcluded == 0);
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
