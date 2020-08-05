package dev.ctdmodding.cubelettask.cubelet.display.impl;

import dev.ctdmodding.cubelettask.cubelet.display.CubeletHead;
import dev.ctdmodding.cubelettask.util.ChatUtil;
import org.bukkit.Location;
import org.bukkit.entity.Item;

/**
 * Jon created on 8/4/2020
 */
public class CtdHead extends CubeletHead {
    public CtdHead() {
        super(ChatUtil.colorize("&5Rarity: &6&lLegendary"), "ewogICJ0aW1lc3RhbXAiIDogMTU5NjU5ODY0MzIwNCwKICAicHJvZmlsZUlkIiA6ICJhYzVhN2Y4NjAxOWI0OTg4OTE4MDc1ODZkODc1NThkYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJjdGQiLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDNhZmY4NDc0NWY5NWMxMDY5Y2U3MTM1ZWE2YTk3NTRhN2FjYzViNGM2NmJjZDEyMWQ2OTlhOWU3MTNhZDA4MyIKICAgIH0KICB9Cn0=");
    }

    public Item drop(Location location) {
        Item drop = location.getWorld().dropItem(location, getDisplayItem());
        drop.setCustomName(ChatUtil.colorize(getDisplayName()));
        drop.setCustomNameVisible(true);
        drop.setInvulnerable(true);
        return drop;
    }
}
