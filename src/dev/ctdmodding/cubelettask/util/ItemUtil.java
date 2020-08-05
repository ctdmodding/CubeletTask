package dev.ctdmodding.cubelettask.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Jon created on 8/5/2020
 */
public class ItemUtil {

    public static ItemStack makeCubeletMachineItem() {
        ItemStack machineItem = new ItemStack(Material.END_PORTAL_FRAME);
        ItemMeta im = machineItem.getItemMeta();
        im.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Cubelet Machine Menu");
        machineItem.setItemMeta(im);
        return machineItem;
    }
}
