package dev.ctdmodding.cubelettask.cubelet.structure;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Jon created on 8/3/2020
 */
public class BlockCache {

    private Location location;
    private Material cache;
    private Material newTexture;

    public BlockCache(Location location, Material newTexture) {
        this.location = location;
        this.cache = location.getBlock().getType();
        this.newTexture = newTexture;
    }

    public BlockCache(Location location, Material cache, Material newTexture) {
        this.location = location;
        this.cache = cache;
        this.newTexture = newTexture;
    }

    public Location getLocation() {
        return location;
    }

    public Material getCache() {
        return cache;
    }

    public Material getNewTexture() {
        return newTexture;
    }

    public void retexture() {
        location.getBlock().setType(newTexture);
    }

    public void revert() {
        location.getBlock().setType(cache);
    }
}
