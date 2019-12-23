package com.github.rz7d.daylightmotd;

@FunctionalInterface
public interface MotdProvider {

    String apply(long worldTime, String defaultMotd);

}
