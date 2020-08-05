package dev.ctdmodding.cubelettask.cubelet;

import dev.ctdmodding.cubelettask.cubelet.display.ICubeletDisplay;
import dev.ctdmodding.cubelettask.cubelet.entity.EntityCubelet;
import dev.ctdmodding.cubelettask.cubelet.interfaces.ICubelet;
import org.bukkit.Location;

/**
 * Jon created on 7/31/2020
 */
public abstract class Cubelet implements ICubelet, Runnable {

    private Location location;
    private ICubeletDisplay cubeletDisplay;
    private EntityCubelet entityCubelet;

    public Cubelet(Location location, ICubeletDisplay cubeletDisplay, EntityCubelet entityCubelet) {
        this.location = location;
        this.cubeletDisplay = cubeletDisplay;
        this.entityCubelet = entityCubelet;
    }

    public Location getLocation() {
        return location;
    }

    public EntityCubelet getEntityCubelet() {
        return entityCubelet;
    }

    public ICubeletDisplay getCubeletDisplay() {
        return cubeletDisplay;
    }
}
