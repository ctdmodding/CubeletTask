package dev.ctdmodding.cubelettask.cubelet;

import dev.ctdmodding.cubelettask.examples.ender.EnderCubelet;

public enum CubeletType {

    NETHER(EnderCubelet.class)
    ;

    private Class<? extends Cubelet> clazz;

    CubeletType(Class<? extends Cubelet> clazz) {
        this.clazz = clazz;
    }
}
