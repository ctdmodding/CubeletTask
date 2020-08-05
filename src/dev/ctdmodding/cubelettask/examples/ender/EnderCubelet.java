package dev.ctdmodding.cubelettask.examples.ender;

import dev.ctdmodding.cubelettask.CubeletPlugin;
import dev.ctdmodding.cubelettask.cubelet.CubeletStructure;
import dev.ctdmodding.cubelettask.cubelet.display.impl.EnderChestHead;
import dev.ctdmodding.cubelettask.cubelet.entity.EntityCubelet;
import dev.ctdmodding.cubelettask.cubelet.machine.CubeletMachine;
import dev.ctdmodding.cubelettask.cubelet.structure.StructureBuilder;
import dev.ctdmodding.cubelettask.examples.ender.structure.NetherStructure;
import dev.ctdmodding.cubelettask.util.MathUtil;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Jon created on 7/31/2020
 */
public class EnderCubelet extends CubeletStructure {

    private BukkitTask rotation;
    private BukkitTask sphere;
    private StructureBuilder netherStructureBuilder;
    private NetherStructure netherStructure;
    private Set<Entity> entities;
    private ArmorStand fireStand;

    public EnderCubelet(Location location, EntityCubelet entityCubelet) {
        super(location, new EnderChestHead(), entityCubelet);
        long decaySeconds = 20 * 20L;
        setStructure(new EnderStructure(this, location, 4 * 20L, decaySeconds));
        netherStructureBuilder = new StructureBuilder(new NetherStructure(location, decaySeconds));
        entities = new HashSet<>();
    }

    public void setFireStand(ArmorStand fireStand) {
        this.fireStand = fireStand;
    }

    public ArmorStand getFireStand() {
        return fireStand;
    }

    public StructureBuilder getNetherStructureBuilder() {
        return netherStructureBuilder;
    }

    @Override
    public void buildStructure() {
        getBuilder().build();
    }

    @Override
    public void build() {
        getLocation().getWorld().setTime(18_000);
        buildStructure();
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getBuilder().hasFinished()) {
                    cancel();
                    return;
                }
                getEntityCubelet().move(new Vector(0, 0.24, 0));
            }
        }.runTaskTimer(CubeletPlugin.getInstance(), 0, getStructure().getBuildTime() / 22);

        rotation = new BukkitRunnable() {
            @Override
            public void run() {
                getEntityCubelet().rotate(12f);
            }
        }.runTaskTimer(CubeletPlugin.getInstance(), 0, 1);

        sphere = new BukkitRunnable() {
            @Override
            public void run() {
                if (getBuilder().hasFinished()) {
                    cancel();
                    return;
                }
                MathUtil.getSphere().forEach(layer -> {
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.PURPLE, 1);
                    getEntityCubelet().getStand().getWorld().spawnParticle(Particle.REDSTONE, getEntityCubelet().getStand().getEyeLocation().clone().add(layer).add(0, 0.3, 0), 1, 0, 0, 0.0, 0, dustOptions);
                });
            }
        }.runTaskTimer(CubeletPlugin.getInstance(), 0, 1);
    }

    @Override
    public void destroy() {
        getEntityCubelet().destroy();
        rotation.cancel();
        entities.forEach(Entity::remove);
        CubeletMachine machine = CubeletPlugin.getInstance().getCubeletMachine();
        machine.setCanUse(true);
        machine.showText();
        netherStructureBuilder.destroy();
        getLocation().getWorld().setTime(1000);
    }

    @Override
    public void run() {

    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addEntity(Collection<? extends Entity> entities) {
        this.entities.addAll(entities);
    }

    public BukkitTask getRotation() {
        return rotation;
    }
}
