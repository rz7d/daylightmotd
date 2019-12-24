package com.github.rz7d.daylightmotd;

import com.github.rz7d.daylightmotd.util.TickDuration;
import com.google.common.collect.ImmutableMap;
import org.bukkit.ChatColor;

import java.util.Map;
import java.util.Optional;
import java.util.function.LongPredicate;

public final class DefaultMotdProvider implements MotdProvider {

    private static final Map<LongPredicate, ChatColor> DEFAULT_COLOR_MAP = ImmutableMap.of(
        TickDuration.between(0, 12517)::test, ChatColor.WHITE,
        TickDuration.between(12517, 13183)::test, ChatColor.DARK_BLUE,
        TickDuration.between(13183, 22917)::test, ChatColor.DARK_GRAY,
        TickDuration.between(22917, 24000)::test, ChatColor.WHITE
    );

    private final Map<LongPredicate, ChatColor> colorMap;

    public DefaultMotdProvider() {
        this(DEFAULT_COLOR_MAP);
    }

    public DefaultMotdProvider(Map<LongPredicate, ChatColor> colorMap) {
        this.colorMap = colorMap;
    }

    Optional<ChatColor> timeToColor(long time) {
        return colorMap.entrySet().stream()
            .filter(e -> e.getKey().test(time))
            .map(Map.Entry::getValue)
            .findAny();
    }

    @Override
    public String apply(long worldTime, String defaultMotd) {
        // Defensive orElse
        return timeToColor(worldTime).orElse(ChatColor.GRAY) + defaultMotd;
    }

}
