package dev.ctdmodding.cubelettask.cubelet.machine.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class MenuPageElement implements Listener {

    private int size;
    private int pageNumber;
    private String pageName;
    private Inventory page;
    private Map<Integer, MenuButtonElement> buttonElements; // <Slot, Item>

    public MenuPageElement(int size, String pageName) {
        this.size = size;
        this.pageName = pageName;
        page = Bukkit.createInventory(null, size, pageName);
        buttonElements = new HashMap<>();
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public MenuPageElement addButtonElement(int slot, MenuButtonElement buttonElement) {
        buttonElements.put(slot, buttonElement);
        page.setItem(slot, buttonElement.getIcon());
        return this;
    }

    public void addGlass() {
        for (int i = 0; i < page.getContents().length; i++) {
            if (page.getItem(i) == null) {
                addButtonElement(i, new MenuButtonElement("", Material.BLACK_STAINED_GLASS_PANE, null, true, false) {
                    @Override
                    public void onClick(Player whoClicked, GameMenu menu) {
                        // Do nothing
                    }
                });
            }
        }
    }

    public Map<Integer, MenuButtonElement> getButtonElements() {
        return buttonElements;
    }

    public Inventory getPage() {
        return page;
    }

    public String getPageName() {
        return pageName;
    }
}
