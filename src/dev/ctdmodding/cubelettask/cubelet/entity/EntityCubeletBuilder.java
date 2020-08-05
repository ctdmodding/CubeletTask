package dev.ctdmodding.cubelettask.cubelet.entity;

import dev.ctdmodding.cubelettask.cubelet.display.CubeletDisplayType;
import dev.ctdmodding.cubelettask.cubelet.display.ICubeletDisplay;
import org.bukkit.Location;

/**
 * Jon created on 7/31/2020
 */
public class EntityCubeletBuilder {

    private EntityCubelet entityCubelet;
    private Location location;
    private ICubeletDisplay display;
    private CubeletDisplayType displayType;

    public EntityCubeletBuilder() {
    }

    public EntityCubeletBuilder(Location location) {
        this.location = location;
    }

    public EntityCubeletBuilder setLocation(Location location) {
        this.location = location;
        return this;
    }

    public EntityCubeletBuilder setDisplay(ICubeletDisplay display) {
        this.display = display;
        return this;
    }

    public EntityCubeletBuilder setDisplayType(CubeletDisplayType displayType) {
        this.displayType = displayType;
        return this;
    }

    public EntityCubeletBuilder buildCublet() {
        this.entityCubelet = new EntityCubelet(location, display, displayType);
        return this;
    }

    public EntityCubeletBuilder spawnArmorStand() {
        entityCubelet.spawnArmorStand();
        return this;
    }

    public EntityCubeletBuilder setVisible(boolean visible) {
        entityCubelet.getStand().setVisible(visible);
        return this;
    }

    public EntityCubeletBuilder setGravity(boolean gravity) {
        entityCubelet.getStand().setGravity(gravity);
        return this;
    }

    public EntityCubeletBuilder setCustomName() {
        setCustomName(display.getDisplayName());
        return this;
    }

    public EntityCubeletBuilder setCustomName(String customName) {
        entityCubelet.getStand().setCustomName(customName);
        return this;
    }

    public EntityCubeletBuilder setCustomNameVisible(boolean visible) {
        entityCubelet.getStand().setCustomNameVisible(visible);
        return this;
    }

    public EntityCubeletBuilder setDisplayItem() {
        entityCubelet.setDisplayItem();
        return this;
    }

    public EntityCubeletBuilder setSmall(boolean small) {
        entityCubelet.getStand().setSmall(small);
        return this;
    }

    public EntityCubeletBuilder setInvulnerable(boolean invulnerable) {
        entityCubelet.getStand().setInvulnerable(invulnerable);
        return this;
    }

    public EntityCubelet getEntityCubelet() {
        return entityCubelet;
    }
}
