package dev.ctdmodding.cubelettask.cubelet.structure;

import java.util.List;

/**
 * Jon created on 8/1/2020
 */
public class DelayedStructureLayer extends StructureLayer {

    private long delay;

    public DelayedStructureLayer(List<StructureElement> elements, long delay) {
        super(elements);
        this.delay = delay;
    }

    public DelayedStructureLayer(long delay) {
        this.delay = delay;
    }

    public DelayedStructureLayer(StructureElement element, long delay) {
        super(element);
        this.delay = delay;
    }

    public long getDelay() {
        return delay;
    }
}
