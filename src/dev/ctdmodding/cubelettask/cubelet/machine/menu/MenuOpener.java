package dev.ctdmodding.cubelettask.cubelet.machine.menu;

import dev.ctdmodding.cubelettask.CubeletPlugin;
import dev.ctdmodding.cubelettask.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.PluginManager;

public class MenuOpener implements Listener {

    private GameMenu gameMenu;

    public MenuOpener(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
        registerOpener();
    }

    public void registerOpener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, CubeletPlugin.getInstance());
    }

    public void unregisterOpener() {
        HandlerList.unregisterAll(this);
    }


    @EventHandler
    public void onMachineInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
        Player player = event.getPlayer();

        if (event.getClickedBlock().getType() == Material.END_PORTAL_FRAME && event.getClickedBlock().getLocation().distance(CubeletPlugin.getInstance().getCubeletMachine().getLocation()) <= 2) {
            if (CubeletPlugin.getInstance().getCubeletMachine().isCanUse()) {
                gameMenu.open(player);
            } else {
                ChatUtil.sendFormattedMessage(player, ChatUtil.colorize("&cPlease wait! The Cubelet Machine is already running."));
            }
        }
    }
}
