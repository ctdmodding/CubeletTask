package dev.ctdmodding.cubelettask.cubelet.structure;

import org.bukkit.Material;
import org.bukkit.util.Vector;

/**
 * Jon created on 8/1/2020
 */
public class StructureElement {

    private Vector offset;
    private Material material;
    private StructureMechanic mechanic;
    private BlockCache blockCache;

    public StructureElement(Vector offset, BlockCache blockCache, StructureMechanic mechanic) {
        this.offset = offset;
        this.blockCache = blockCache;
        this.mechanic = mechanic;
    }

    public StructureElement(Vector offset, BlockCache blockCache) {
        this(offset, blockCache, null);
    }

    public BlockCache getBlockCache() {
        return blockCache;
    }

    public boolean isCache() {
        return blockCache == null;
    }

//    public StructureElement(Vector offset, Material material, StructureMechanic mechanic) {
//        this.offset = offset;
//        this.material = material;
//        this.mechanic = mechanic;
//    }

    public Vector getOffset() {
        return offset;
    }

    public Material getMaterial() {
        return material;
    }

    public StructureMechanic getMechanic() {
        return mechanic;
    }

    public boolean hasMechanic() {
        return mechanic != null;
    }
}
