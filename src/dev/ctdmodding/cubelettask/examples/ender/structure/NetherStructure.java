package dev.ctdmodding.cubelettask.examples.ender.structure;

import dev.ctdmodding.cubelettask.cubelet.structure.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

/**
 * Jon created on 8/4/2020
 */
public class NetherStructure extends Structure {

    public NetherStructure(Location location, long decaySeconds) {
        super(location, 20L * 4, decaySeconds);
        addFloorLayer();
        addNetherBricks();
        addSlabs();
        addNetherrack();
        addFireLayers(Material.FIRE, 5L);
        addFireLayers(Material.AIR, 20L);
        addFireLayers(Material.FIRE, 15L);
        addFireLayers(Material.AIR, 20L);
        addFireLayers(Material.FIRE, 25L);
    }

    public void addFloorLayer() {
        int radius = 1;
        StructureLayer floor = new StructureLayer();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Vector current = new Vector(x, -1, z);
                floor.addElement(new StructureElement(current, new BlockCache(getLocation().clone().add(current), Material.NETHER_BRICKS)));
            }
        }
        addLayer(floor);
    }

    public void addToLayer(StructureLayer layer, Vector offset, Material material) {
        layer.addElement(new StructureElement(offset, new BlockCache(getLocation().clone().add(offset), material)));
    }

    public void addNetherrack() {
        StructureLayer netherrack = new StructureLayer();
        addToLayer(netherrack, new Vector(-2, -1, -2), Material.NETHERRACK);
        addToLayer(netherrack, new Vector(-2, -1, 2), Material.NETHERRACK);
        addToLayer(netherrack, new Vector(2, -1, -2), Material.NETHERRACK);
        addToLayer(netherrack, new Vector(2, -1, 2), Material.NETHERRACK);
        addLayer(netherrack);
    }

    public void addNetherBricks() {
        StructureLayer netherBricks = new StructureLayer();
        addToLayer(netherBricks, new Vector(-2, -1, 0), Material.RED_NETHER_BRICKS);
        addToLayer(netherBricks, new Vector(0, -1, 2), Material.RED_NETHER_BRICKS);
        addToLayer(netherBricks, new Vector(2, -1, 0), Material.RED_NETHER_BRICKS);
        addToLayer(netherBricks, new Vector(0, -1, -2), Material.RED_NETHER_BRICKS);
        addLayer(netherBricks);
    }

    public void addSlabs() {
        StructureLayer slabs = new StructureLayer();

        addToLayer(slabs, new Vector(-2, -1, -1), Material.RED_NETHER_BRICK_SLAB);
        addToLayer(slabs, new Vector(-2, -1, 1), Material.RED_NETHER_BRICK_SLAB);

        addToLayer(slabs, new Vector(-1, -1, 2), Material.RED_NETHER_BRICK_SLAB);
        addToLayer(slabs, new Vector(1, -1, 2), Material.RED_NETHER_BRICK_SLAB);

        addToLayer(slabs, new Vector(2, -1, -1), Material.RED_NETHER_BRICK_SLAB);
        addToLayer(slabs, new Vector(2, -1, 1), Material.RED_NETHER_BRICK_SLAB);

        addToLayer(slabs, new Vector(1, -1, -2), Material.RED_NETHER_BRICK_SLAB);
        addToLayer(slabs, new Vector(-1, -1, -2), Material.RED_NETHER_BRICK_SLAB);
        addLayer(slabs);
    }

    public void addFireLayers(Material material, long delay) {
        makeFireLayer(new Vector(-2, 0, -2), material, delay);
        makeFireLayer(new Vector(2, 0, -2), material, delay);
        makeFireLayer(new Vector(2, 0, 2), material, delay);
        makeFireLayer(new Vector(-2, 0, 2), material, delay);
    }

    public void makeFireLayer(Vector offset, Material material, long delay) {
        addLayer(new DelayedStructureLayer(new StructureElement(offset, new BlockCache(getLocation().clone().add(offset), material)), delay));
    }
}
