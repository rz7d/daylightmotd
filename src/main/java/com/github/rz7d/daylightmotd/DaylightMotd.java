package com.github.rz7d.daylightmotd;

import org.bukkit.plugin.java.JavaPlugin;

public class DaylightMotd extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer()
            .getPluginManager()
            .registerEvents(new PingListener(this, new DefaultMotdProvider()), this);
    }

}
