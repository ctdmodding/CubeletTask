package dev.ctdmodding.cubelettask.examples.ender.mechanic;

import dev.ctdmodding.cubelettask.CubeletPlugin;
import dev.ctdmodding.cubelettask.cubelet.structure.StructureMechanic;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Jon created on 8/3/2020
 */
public class DestroyMechanic extends StructureMechanic {

    private Location location;
    private long delay;

    public DestroyMechanic(Location location, long delay) {
        this.location = location;
        this.delay = delay;
    }

    @Override
    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                location.getBlock().setType(Material.AIR);
            }
        }.runTaskLater(CubeletPlugin.getInstance(), delay);
    }
}
