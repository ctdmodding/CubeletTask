package dev.ctdmodding.cubelettask.cubelet.structure;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Jon created on 8/1/2020
 */
public class Structure {

    private Location location;
    private long buildTime;
    private long decaySeconds;
    private List<StructureLayer> layers;
    private List<Block> blocks;
    private List<BlockCache> cache;
    private StructureMechanic mechanic;

    public Structure(Location location, long buildTime, long decaySeconds) {
        this.location = location;
        this.buildTime = buildTime;
        this.decaySeconds = decaySeconds;
        layers = new ArrayList<>();
        blocks = new ArrayList<>();
        cache = new ArrayList<>();
    }

    public void setMechanic(StructureMechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Location getLocation() {
        return location;
    }

    public void addLayer(StructureLayer... layer) {
        layers.addAll(Arrays.asList(layer));
    }

    public List<StructureLayer> getLayers() {
        return layers;
    }

    public long getBuildTime() {
        return buildTime;
    }

    public long getDecaySeconds() {
        return decaySeconds;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void addBlock(Block block) {
        cache.add(new BlockCache(block.getLocation(), Material.AIR));
    }

    public void addBlock(BlockCache blockCache) {
        cache.add(blockCache);
    }

    public void destroy() {
        //blocks.forEach(block -> block.setType(Material.AIR));
        cache.forEach(BlockCache::revert);
    }
}
