package com.github.rz7d.daylightmotd;

import com.github.rz7d.daylightmotd.util.TickDuration;
import com.google.common.collect.ImmutableMap;
import org.bukkit.ChatColor;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.LongPredicate;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultMotdProviderTest {

    private static final Map<LongPredicate, ChatColor> COLOR_MAP = ImmutableMap.of(
        TickDuration.between(0, 12517)::test, ChatColor.WHITE,
        TickDuration.between(12517, 13183)::test, ChatColor.DARK_BLUE,
        TickDuration.between(13183, 22917)::test, ChatColor.DARK_GRAY,
        TickDuration.between(22917, 24000)::test, ChatColor.WHITE
    );

    private final DefaultMotdProvider motdProvider = new DefaultMotdProvider(COLOR_MAP);

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void testTimeToColor() {
        // range test
        assertEquals(ChatColor.WHITE, motdProvider.timeToColor(0).get());
        assertEquals(ChatColor.DARK_BLUE, motdProvider.timeToColor(12517).get());
        assertEquals(ChatColor.DARK_GRAY, motdProvider.timeToColor(13183).get());
        assertEquals(ChatColor.WHITE, motdProvider.timeToColor(22917).get());

        // normalization test
        assertEquals(ChatColor.WHITE, motdProvider.timeToColor(23000 + 24000 + 24000).get());

        // not empty test
        for (int i = 0; i < 2400000; ++i) {
            assertTrue(motdProvider.timeToColor(i).isPresent());
        }
    }

    @Test
    public void testApply() {
        final String defaultMotd = "D3f4u1tM0tDMess@ge";
        assertEquals(ChatColor.WHITE + defaultMotd, motdProvider.apply(0, defaultMotd));
        assertEquals(ChatColor.DARK_BLUE + defaultMotd, motdProvider.apply(12517, defaultMotd));
        assertEquals(ChatColor.DARK_GRAY + defaultMotd, motdProvider.apply(13183, defaultMotd));
        assertEquals(ChatColor.WHITE + defaultMotd, motdProvider.apply(22917, defaultMotd));
    }

}
