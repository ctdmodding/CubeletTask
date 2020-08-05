package dev.ctdmodding.cubelettask.cubelet.display;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Jon created on 7/31/2020
 */
public class CubeletBlock implements ICubeletDisplay {

    private Material blockDisplay;

    public CubeletBlock(Material blockDisplay) {
        this.blockDisplay = blockDisplay;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(blockDisplay);
    }

    @Override
    public String getDisplayName() {
        return null;
    }
}
