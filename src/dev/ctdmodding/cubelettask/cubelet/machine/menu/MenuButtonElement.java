package dev.ctdmodding.cubelettask.cubelet.machine.menu;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class MenuButtonElement {

    private ItemStack icon;
    private boolean clickable;
    private String itemName;
    private Material material;
    private List<String> lore;

    public MenuButtonElement(String itemName, Material material, List<String> lore, boolean enchanted, boolean clickable) {
        this.itemName = itemName;
        this.material = material;
        this.lore = lore;
        this.clickable = clickable;

        icon = new ItemStack(material);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(itemName);
        if (lore != null) {
            meta.setLore(lore);
        }
        if (enchanted) {
            icon.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        icon.setItemMeta(meta);
    }

    public MenuButtonElement(String itemName, Material material) {
        this(itemName, material, null, false, true);
    }

    public ItemStack getIcon() {
        return icon;
    }

    public String getItemName() {
        return itemName;
    }

    public List<String> getLore() {
        return lore;
    }

    public boolean isClickable() {
        return clickable;
    }

    public abstract void onClick(Player whoClicked, GameMenu menu);
}
