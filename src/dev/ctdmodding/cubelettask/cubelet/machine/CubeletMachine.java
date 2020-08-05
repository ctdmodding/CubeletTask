package dev.ctdmodding.cubelettask.cubelet.machine;

import dev.ctdmodding.cubelettask.cubelet.display.CubeletArmorStand;
import dev.ctdmodding.cubelettask.cubelet.machine.menu.CubeletMachineMenu;
import dev.ctdmodding.cubelettask.util.ChatUtil;
import dev.ctdmodding.cubelettask.util.LocationUtil;
import org.bukkit.Location;

/**
 * Jon created on 8/5/2020
 */
public class CubeletMachine {

    private Location location;
    private boolean canUse;
    private CubeletArmorStand stand;

    public CubeletMachine(Location location) {
        this.location = location;
        this.canUse = true;
        new CubeletMachineMenu("Cubelet Machine", 9 * 5);
        stand = new CubeletArmorStand(LocationUtil.getFixedLocation(location).subtract(0, 1, 0), ChatUtil.colorize("&e&lCubelet Machine"));
        stand.addDisplayLine(ChatUtil.colorize("&dClick to open"));
    }

    public boolean isCanUse() {
        return canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    public Location getLocation() {
        return location;
    }

    public void hideText() {
        stand.getStand().setCustomNameVisible(false);
        stand.getDisplayLines().forEach(line -> line.setCustomNameVisible(false));
    }

    public void showText() {
        stand.getStand().setCustomNameVisible(true);
        stand.getDisplayLines().forEach(line -> line.setCustomNameVisible(true));
    }
}
