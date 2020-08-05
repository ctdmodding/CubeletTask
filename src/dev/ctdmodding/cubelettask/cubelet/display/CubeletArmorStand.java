package dev.ctdmodding.cubelettask.cubelet.display;

import dev.ctdmodding.cubelettask.util.ChatUtil;
import dev.ctdmodding.cubelettask.util.WorldUtil;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Jon created on 7/31/2020
 */
public class CubeletArmorStand {

    private Location location;
    private ICubeletDisplay cubeletDisplay;
    private CubeletDisplayType displayType;
    private ArmorStand stand;
    private List<ArmorStand> displayLines;

    public CubeletArmorStand(Location location) {
        this.location = location;
        this.displayLines = new ArrayList<>();
        this.stand = WorldUtil.spawnArmorStand(location);
    }

    public CubeletArmorStand(Location location, ICubeletDisplay cubeletDisplay, CubeletDisplayType displayType) {
        this(location);
        this.location = location;
        this.cubeletDisplay = cubeletDisplay;
        this.displayType = displayType;
        setDisplayItem();
    }

    public CubeletArmorStand(Location location, String displayText) {
        this(location);
        setArmorstandValues(stand, displayText);
    }

    public void addDisplayLine(String text) {
        ArmorStand displayLine = WorldUtil.spawnArmorStand(location.clone().subtract(0, (double)displayLines.size() / 2 + 0.23, 0));
        displayLines.add(setArmorstandValues(displayLine, text));
    }

    public ArmorStand setArmorstandValues(ArmorStand stand) {
        return setArmorstandValues(stand, null);
    }

    public ArmorStand setArmorstandValues() {
        return setArmorstandValues(stand, null);
    }

    public ArmorStand setArmorstandValues(ArmorStand stand, String text) {
        if (text != null) {
            stand.setCustomName(ChatUtil.colorize(text));
            stand.setCustomNameVisible(true);
        }
        stand.setVisible(false);
        stand.setInvulnerable(true);
        stand.setGravity(false);
        stand.setCanPickupItems(false);
        stand.setCollidable(false);
        return stand;
    }

    public ICubeletDisplay getCubeletDisplay() {
        return cubeletDisplay;
    }

    public ArmorStand getStand() {
        return stand;
    }

    public boolean hasCubeletDisplay() {
        return cubeletDisplay != null;
    }

    public void destroy() {
        stand.remove();
        displayLines.forEach(Entity::remove);
    }

    public List<ArmorStand> getDisplayLines() {
        return displayLines;
    }

    public void setDisplayItem() {
        switch (displayType) {
            case HELMET:
                stand.setHelmet(cubeletDisplay.getDisplayItem());
                break;
            case PASSENGER:
                Item item = location.getWorld().dropItem(location, cubeletDisplay.getDisplayItem());
                item.setCustomNameVisible(true);
                item.setCustomName(cubeletDisplay.getDisplayName());
                item.setInvulnerable(true);
                stand.addPassenger(item);
                break;
        }
    }
}
