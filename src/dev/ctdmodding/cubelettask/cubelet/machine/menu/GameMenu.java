package dev.ctdmodding.cubelettask.cubelet.machine.menu;

import dev.ctdmodding.cubelettask.CubeletPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import java.util.HashMap;
import java.util.Map;

public class GameMenu implements Listener {

    private String displayName;
    private int rows;
    private Inventory menu;
//    private Map<Integer, MenuButtonElement> elements; // <Slot, Item>
    private Map<Integer, MenuPageElement> pageElements;
    private MenuButtonElement nextPageButton;
    private MenuButtonElement previousPageButton;
    private int currentPage = 0;
    private ItemStack displayItem;
    private boolean opened;
    private MenuOpener menuOpener;
    private int inventorySlot = -1;

    public GameMenu(ItemStack displayItem, String displayName, int rows) {
        this.displayItem = displayItem;
        //elements = new HashMap<>();
        pageElements = new HashMap<>();
        nextPageButton = new MenuButtonElement(ChatColor.YELLOW + "Next Page", Material.ARROW) {
            @Override
            public void onClick(Player whoClicked, GameMenu menu) {
                currentPage++;
                //ChatUtil.debug("Current Page: " + (currentPage + 1) + "/" + pageElements.size(), true);
                MenuPageElement page = pageElements.get(currentPage);
                if (page != null) {
                    Inventory pageInventory = page.getPage();
                    whoClicked.openInventory(pageInventory);
                } else {
                    //ChatUtil.debug("Page is null", true);
                }
            }
        };
        previousPageButton = new MenuButtonElement(ChatColor.YELLOW + "Previous Page", Material.ARROW) {
            @Override
            public void onClick(Player whoClicked, GameMenu menu) {
                if (currentPage > 0) {
                    currentPage--;
                    whoClicked.openInventory(pageElements.get(currentPage).getPage());
                }
            }
        };
        menuOpener = new MenuOpener(this);
        registerMenu();
    }

    public GameMenu addPage(MenuPageElement pageElement) {
        int size = pageElements.size();
        int pageNumber = 0;
        if (size > 0) {
            pageNumber = size;
        }
        pageElement.setPageNumber(pageNumber);
        pageElements.put(pageNumber, pageElement);
        return this;
    }

    public void registerMenu() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, CubeletPlugin.getInstance());
        //pageElements.values().forEach(page -> pm.registerEvents(page, GameFramework.getInstance().getPlugin()));
    }

    public void unregisterMenu() {
        PluginManager pm = Bukkit.getPluginManager();
        HandlerList.unregisterAll(this);
    }

    public void destroy() {
        unregisterMenu();
        menuOpener.unregisterOpener();
    }

    public void destroy(Player player) {
        destroy();
        player.closeInventory();
        if (inventorySlot > -1) {
            player.getInventory().clear(inventorySlot);
        }
    }

    public void open(Player player) {
        player.openInventory(pageElements.get(currentPage).getPage());
        opened = true;
    }

    public void close(Player player) {
        player.closeInventory();
    }

    public void addPageButtons(int nextSlot, int previousSlot) {
        if (pageElements.size() > 1) {
            int pages = pageElements.size() - 1;
            for (int i = 0; i <= pages; i++) {
                if (i == 0) {
                    pageElements.get(i).addButtonElement(nextSlot, nextPageButton);
                } else if (i < pages) {
                    pageElements.get(i).addButtonElement(previousSlot, previousPageButton);
                    pageElements.get(i).addButtonElement(nextSlot, nextPageButton);
                } else if (i == pages) {
                    pageElements.get(i).addButtonElement(previousSlot, previousPageButton);
                }
            }
        }
    }

    public MenuPageElement getCurrentPage() {
        return pageElements.get(currentPage);
    }

    public String getDisplayName() {
        return displayName;
    }

    public ItemStack getDisplayItem() {
        return displayItem;
    }

    public void giveOpenItem(Player player, int inventorySlot) {
        this.inventorySlot = inventorySlot;
        player.getInventory().setItem(inventorySlot, displayItem);
    }

    public int getInventorySlot() {
        return inventorySlot;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent clickEvent) {
        MenuPageElement currentPageElement = getCurrentPage();

        if (currentPageElement != null) {
            if (clickEvent.getView().getTitle().equals(currentPageElement.getPageName()) && clickEvent.getWhoClicked() instanceof Player) {
                Player whoClicked = (Player) clickEvent.getWhoClicked();
                ItemStack currentItem = clickEvent.getCurrentItem();
                MenuButtonElement clickedButton;

                if (currentItem != null) {
                    currentPageElement.getButtonElements().values().forEach(button -> {
                        if (button.getItemName().equals(currentItem.getItemMeta().getDisplayName())) {
                            if (button.isClickable()) {
                                button.onClick(whoClicked, this);
                            }
                        }
                    });
                }
                clickEvent.setCancelled(true);
            }
        }
    }
}
