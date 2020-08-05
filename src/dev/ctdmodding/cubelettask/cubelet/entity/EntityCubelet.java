package dev.ctdmodding.cubelettask.cubelet.entity;

import dev.ctdmodding.cubelettask.cubelet.display.CubeletDisplayType;
import dev.ctdmodding.cubelettask.cubelet.display.ICubeletDisplay;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;

/**
 * Jon created on 7/31/2020
 */
public class EntityCubelet {

    private Location location;
    private ICubeletDisplay cubeletDisplay;
    private CubeletDisplayType displayType;
    private ArmorStand stand;

    public EntityCubelet(Location location, ICubeletDisplay cubeletDisplay, CubeletDisplayType displayType) {
        this.location = location;
        this.cubeletDisplay = cubeletDisplay;
        this.displayType = displayType;
    }

    public void spawnArmorStand() {
        this.stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
    }

    public void setupArmorStand() {
        spawnArmorStand();
        stand.setVisible(false);
        stand.setCustomName(cubeletDisplay.getDisplayName());
        stand.setCustomNameVisible(true);
        setDisplayItem();
    }

    public void setDisplayItem() {
        switch (displayType) {
            case HELMET:
                stand.setHelmet(cubeletDisplay.getDisplayItem());
                break;
            case PASSENGER:
                stand.addPassenger(location.getWorld().dropItem(location, cubeletDisplay.getDisplayItem()));
                break;
        }
    }

    public ArmorStand getStand() {
        return stand;
    }

    public void move(Vector offset) {
        stand.teleport(stand.getLocation().clone().add(offset.getX(), offset.getY(), offset.getZ()));
    }

    public void rotate(float yaw) {
        Location loc = stand.getLocation();
        loc.setYaw(loc.getYaw() + yaw);
        stand.teleport(loc);
    }

    public void addVelocity(Vector velocity) {
        stand.setVelocity(velocity);
    }

    public void spawn() {

    }

    public void passengerToHelmet() {
        stand.getPassengers().forEach(Entity::remove);
        stand.setHelmet(cubeletDisplay.getDisplayItem());
        stand.setCustomNameVisible(true);
    }

    public void helmetToPassenger() {
        stand.setHelmet(null);
        stand.setCustomNameVisible(false);
        Item dropItem = stand.getLocation().getWorld().dropItem(stand.getLocation(), cubeletDisplay.getDisplayItem());
        dropItem.setCustomName(cubeletDisplay.getDisplayName());
        dropItem.setCustomNameVisible(true);
        dropItem.setInvulnerable(true);
        stand.addPassenger(dropItem);
    }

    public void destroy() {
        if (stand.getPassengers().size() > 0) {
            stand.getPassengers().forEach(Entity::remove);
        }
        stand.remove();
    }
}
