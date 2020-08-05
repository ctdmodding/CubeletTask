package dev.ctdmodding.cubelettask.cubelet.display;

import dev.ctdmodding.cubelettask.util.SkullUtil;
import org.bukkit.inventory.ItemStack;

/**
 * Jon created on 7/31/2020
 */
public class CubeletHead implements ICubeletDisplay {

    private String displayName;
    private String base64Texture;

    public CubeletHead(String displayName, String base64Texture) {
        this.displayName = displayName;
        this.base64Texture = base64Texture;
    }

    public String getBase64Texture() {
        return base64Texture;
    }

    @Override
    public ItemStack getDisplayItem() {
        return SkullUtil.makeTextureSkull(base64Texture);
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
