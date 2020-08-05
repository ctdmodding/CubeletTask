package dev.ctdmodding.cubelettask.examples.ender;

import dev.ctdmodding.cubelettask.cubelet.structure.*;
import dev.ctdmodding.cubelettask.examples.ender.mechanic.DestroyMechanic;
import dev.ctdmodding.cubelettask.examples.ender.mechanic.FireMechanic;
import dev.ctdmodding.cubelettask.examples.ender.mechanic.NetherMechanic;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

/**
 * Jon created on 8/1/2020
 */
public class EnderStructure extends Structure {

    private EnderCubelet cubelet;

    public EnderStructure(EnderCubelet cubelet, Location location, long buildTime, long decaySeconds) {
        super(location, buildTime, decaySeconds);
        this.cubelet = cubelet;
        int yOffset;
        addSupport();
        for (yOffset = 0; yOffset < 6; yOffset++) {
            if (yOffset < 5) {
                addLayer(makeFenceLayer(location, Material.NETHER_BRICK_FENCE, 2, yOffset, 2));
                if (yOffset == 4) {
                    addLayer(makeFenceLayer(location, Material.NETHER_BRICK_FENCE, 1, yOffset, 1));
                }
            } else {
                addLayer(makeFenceLayer(location, Material.NETHER_BRICK_FENCE, 1, yOffset, 1));
            }
        }
        Vector netherrackOffset = new Vector(0, 4, 0);
        Vector fireOffset = new Vector(0, 5, 0);

        addLayer(new DelayedStructureLayer(new StructureElement(new Vector(0, 4, 0), new BlockCache(location.clone().add(netherrackOffset), Material.NETHERRACK), new DestroyMechanic(location.clone().add(netherrackOffset), 20L * 4)), 20L));
        addLayer(new DelayedStructureLayer(new StructureElement(fireOffset, new BlockCache(location.clone().add(fireOffset), Material.FIRE), new NetherMechanic(cubelet, location, fireOffset)), 30L));
    }

    public StructureLayer makeFenceLayer(Location location, Material material, int xOffset, int yOffset, int zOffset) {
        StructureLayer layer = new StructureLayer();
        Vector offset1 = new Vector(0, yOffset, zOffset);
        Vector offset2 = new Vector(0, yOffset, -zOffset);
        Vector offset3 = new Vector(xOffset, yOffset, 0);
        Vector offset4 = new Vector(-xOffset, yOffset, 0);
        StructureElement[] elements = {
                new StructureElement(offset1, new BlockCache(location.clone().add(offset1), material), new FireMechanic(location.clone().add(offset1))),
                new StructureElement(offset2, new BlockCache(location.clone().add(offset2), material), new FireMechanic(location.clone().add(offset2))),
                new StructureElement(offset3, new BlockCache(location.clone().add(offset3), material), new FireMechanic(location.clone().add(offset3))),
                new StructureElement(offset4, new BlockCache(location.clone().add(offset4), material), new FireMechanic(location.clone().add(offset4)))
        };
        layer.addElement(elements);

        return layer;
    }

    public void addSupport() {
        StructureLayer support = new StructureLayer();
        addToLayer(support, new Vector(-2, -1, 0), Material.OBSIDIAN);
        addToLayer(support, new Vector(0, -1, 2), Material.OBSIDIAN);
        addToLayer(support, new Vector(2, -1, 0), Material.OBSIDIAN);
        addToLayer(support, new Vector(0, -1, -2), Material.OBSIDIAN);
        addLayer(support);
    }

    public void addToLayer(StructureLayer layer, Vector offset, Material material) {
        layer.addElement(new StructureElement(offset, new BlockCache(getLocation().clone().add(offset), material)));
    }
}
