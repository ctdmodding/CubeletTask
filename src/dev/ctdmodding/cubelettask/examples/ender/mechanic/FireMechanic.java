package dev.ctdmodding.cubelettask.examples.ender.mechanic;

import dev.ctdmodding.cubelettask.CubeletPlugin;
import dev.ctdmodding.cubelettask.cubelet.structure.StructureMechanic;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Jon created on 8/3/2020
 */
public class FireMechanic extends StructureMechanic {

    private Location location;
    public FireMechanic(Location location) {
        this.location = location;
    }

    @Override
    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                location.getBlock().setType(Material.NETHER_BRICK_WALL);
            }
        }.runTaskLater(CubeletPlugin.getInstance(), 20L * 8 + 5);
    }
}
