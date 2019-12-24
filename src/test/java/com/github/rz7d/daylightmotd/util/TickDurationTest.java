package com.github.rz7d.daylightmotd.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TickDurationTest {

    @Test
    public void testBetween() {
        assertDoesNotThrow(() -> TickDuration.between(0, 0));
        assertDoesNotThrow(() -> TickDuration.between(20000, 200000));
        assertThrows(IllegalStateException.class, () -> TickDuration.between(-1, 0));
        assertThrows(IllegalStateException.class, () -> TickDuration.between(0, -1));
        assertThrows(IllegalStateException.class, () -> TickDuration.between(100, 0));
        assertThrows(IllegalStateException.class, () -> TickDuration.between(20000, 0));
        assertDoesNotThrow(() -> TickDuration.between(0, 0));
    }

    @Test
    public void testTest() {
        final TickDuration duration = TickDuration.between(0, 20000);
        assertTrue(() -> duration.test(200));
        assertFalse(() -> duration.test(22200));
        assertTrue(() -> duration.test(24000));
    }

}
