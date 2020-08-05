package dev.ctdmodding.cubelettask.cubelet;

import dev.ctdmodding.cubelettask.cubelet.display.ICubeletDisplay;
import dev.ctdmodding.cubelettask.cubelet.entity.EntityCubelet;
import dev.ctdmodding.cubelettask.cubelet.interfaces.ICubeletStructure;
import dev.ctdmodding.cubelettask.cubelet.structure.Structure;
import dev.ctdmodding.cubelettask.cubelet.structure.StructureBuilder;
import org.bukkit.Location;

/**
 * Jon created on 7/31/2020
 */
public abstract class CubeletStructure extends Cubelet implements ICubeletStructure {

    private Structure structure;
    private StructureBuilder builder;

    public CubeletStructure(Location location, ICubeletDisplay cubeletDisplay, EntityCubelet entityCubelet) {
        super(location, cubeletDisplay, entityCubelet);
        builder = new StructureBuilder(this);
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Structure getStructure() {
        return structure;
    }

    public StructureBuilder getBuilder() {
        return builder;
    }
}
