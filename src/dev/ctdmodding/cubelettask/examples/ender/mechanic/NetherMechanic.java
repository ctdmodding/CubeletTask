package dev.ctdmodding.cubelettask.examples.ender.mechanic;

import dev.ctdmodding.cubelettask.CubeletPlugin;
import dev.ctdmodding.cubelettask.cubelet.display.CubeletArmorStand;
import dev.ctdmodding.cubelettask.cubelet.display.CubeletDisplayType;
import dev.ctdmodding.cubelettask.cubelet.display.impl.CtdHead;
import dev.ctdmodding.cubelettask.cubelet.structure.StructureBuilder;
import dev.ctdmodding.cubelettask.cubelet.structure.StructureMechanic;
import dev.ctdmodding.cubelettask.examples.ender.EnderCubelet;
import dev.ctdmodding.cubelettask.util.LocationUtil;
import dev.ctdmodding.cubelettask.util.WorldUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;

/**
 * Jon created on 8/3/2020
 */
public class NetherMechanic extends StructureMechanic {

    private EnderCubelet cubelet;
    private Location location;
    private Vector offset;

    public NetherMechanic(EnderCubelet cubelet, Location location, Vector offset) {
        this.cubelet = cubelet;
        this.location = location;
        this.offset = offset;
    }

    @Override
    public void run() {
        Objects.requireNonNull(location.getWorld()).strikeLightningEffect(location.clone().add(offset));
        ArmorStand stand = cubelet.getEntityCubelet().getStand();
        stand.setSmall(false);
        new BukkitRunnable() {
            @Override
            public void run() {
                //cubelet.getEntityCubelet().helmetToPassenger();
                ArmorStand fireStand = WorldUtil.spawnArmorStand(stand.getLocation());
                fireStand.setFireTicks(Integer.MAX_VALUE);
                fireStand.setVelocity(new Vector(0, 1.5, 0));
                fireStand.setGravity(true);
                fireStand.setVisible(false);
                fireStand.setInvulnerable(true);
                fireStand.setSmall(true);
                cubelet.addEntity(fireStand);
                cubelet.setFireStand(fireStand);

                stand.setGravity(true);
                stand.setVelocity(new Vector(0, 5, 0));
                //cubelet.getRotation().cancel();
            }
        }.runTaskLater(CubeletPlugin.getInstance(), 20L * 2);

        new BukkitRunnable() {
            @Override
            public void run() {
                cubelet.getNetherStructureBuilder().build();
            }
        }.runTaskLater(CubeletPlugin.getInstance(), 20L * 6);

        new BukkitRunnable() {
            @Override
            public void run() {
                Location fixed = LocationUtil.getFixedLocation(cubelet.getLocation());
                CubeletArmorStand hologram = new CubeletArmorStand(fixed.clone().subtract(0, 0.28, 0), "&aYou found");
                hologram.addDisplayLine("&c&lctd &e&lYour New Developer");
                cubelet.addEntity(hologram.getStand());
                cubelet.addEntity(hologram.getDisplayLines());

                location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, cubelet.getLocation(), 1);
                location.getWorld().playSound(cubelet.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10, 0);
                cubelet.getRotation().cancel();
                cubelet.getEntityCubelet().destroy();
                cubelet.getFireStand().remove();

                CubeletArmorStand item = new CubeletArmorStand(fixed.clone().subtract(0, 0.5, 0), new CtdHead(), CubeletDisplayType.PASSENGER);
                item.setArmorstandValues();
                cubelet.addEntity(item.getStand());
                item.getStand().getPassengers().forEach(cubelet::addEntity);
            }
        }.runTaskLater(CubeletPlugin.getInstance(), 20L * 7);
    }
}
