package dev.ctdmodding.cubelettask.cubelet.structure;

import dev.ctdmodding.cubelettask.CubeletPlugin;
import dev.ctdmodding.cubelettask.cubelet.CubeletStructure;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Jon created on 8/1/2020
 */
public class StructureBuilder {

    private CubeletStructure cubeletStructure;
    private Structure structure;
    private boolean finished;
    private BukkitTask destroyTask;

    public StructureBuilder(CubeletStructure cubeletStructure) {
        this.cubeletStructure = cubeletStructure;
    }

    public StructureBuilder(Structure structure) {
        this.structure = structure;
    }

    public boolean hasFinished() {
        return finished;
    }

    public void build() {
        if (structure == null) {
            structure = cubeletStructure.getStructure();
        }
        long buildInterval = structure.getBuildTime() / structure.getLayers().size();

        Location start = structure.getLocation();
        new BukkitRunnable() {
            private int index = 0;
            private StructureLayer currentLayer;

            @Override
            public void run() {
                if (index >= structure.getLayers().size()) {
                    finished = true;
                    if (structure.getDecaySeconds() > 0) {
                        destroyTask = new BukkitRunnable() {
                            @Override
                            public void run() {
                                destroy();
                            }
                        }.runTaskLater(CubeletPlugin.getInstance(), structure.getDecaySeconds());
                    }
                    cancel();
                    return;
                }

                currentLayer = structure.getLayers().get(index);
                if (currentLayer instanceof DelayedStructureLayer) {
                    DelayedStructureLayer delayedLayer = (DelayedStructureLayer) currentLayer;
                    new BukkitRunnable() {
                        int delayedIndex = index;
                        @Override
                        public void run() {
                            System.out.println("Printing delayed layer: " + delayedIndex);
                            buildLayer(delayedLayer);
                        }
                    }.runTaskLater(CubeletPlugin.getInstance(), delayedLayer.getDelay());
                } else {
                    buildLayer(currentLayer);
                }
                index++;
            }

            public void buildLayer(StructureLayer layer) {
                Block currentBlock;
                for (StructureElement element : layer.getElements()) {
                    currentBlock = start.clone().add(element.getOffset()).getBlock();
                    currentBlock.setType(element.getBlockCache().getNewTexture());
                    structure.addBlock(element.getBlockCache());
                    if (element.hasMechanic()) {
                        element.getMechanic().run();
                    }
                }
            }
        }.runTaskTimer(CubeletPlugin.getInstance(), 0, buildInterval);
    }

    public void destroy() {
        if (cubeletStructure != null) {
            cubeletStructure.destroy();
        }
        if (destroyTask != null) {
            destroyTask.cancel();
        }
        structure.destroy();
    }
}
