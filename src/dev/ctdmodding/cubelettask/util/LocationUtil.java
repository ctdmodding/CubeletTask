package dev.ctdmodding.cubelettask.util;

import org.bukkit.Location;

/**
 * Jon created on 8/1/2020
 */
public class LocationUtil {

    public static Location getFixedLocation(Location location) {
        return new Location(location.getWorld(), Math.floor(location.getX()) + 0.5, Math.floor(location.getY()), Math.floor(location.getZ()) + 0.5);
    }
}
