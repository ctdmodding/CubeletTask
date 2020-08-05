package dev.ctdmodding.cubelettask.cubelet.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Jon created on 8/1/2020
 */
public class StructureLayer {

    private List<StructureElement> elements;

    public StructureLayer(List<StructureElement> elements) {
        this.elements = elements;
    }

    public StructureLayer() {
        elements = new ArrayList<>();
    }

    public StructureLayer(StructureElement element) {
        this();
        elements.add(element);
    }

    public void addElement(StructureElement... elements) {
        this.elements.addAll(Arrays.asList(elements));
    }

    public List<StructureElement> getElements() {
        return elements;
    }
}
