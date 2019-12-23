package com.github.rz7d.daylightmotd;

import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

public class PingListener implements Listener {

    private final Plugin plugin;
    private final MotdProvider motdProvider;

    public PingListener(Plugin plugin, MotdProvider motdProvider) {
        this.plugin = plugin;
        this.motdProvider = motdProvider;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        final Server server = plugin.getServer();
        server.getWorlds().stream().findFirst()
            .map(world -> motdProvider.apply(world.getTime(), event.getMotd()))
            .ifPresent(event::setMotd);
    }

}
