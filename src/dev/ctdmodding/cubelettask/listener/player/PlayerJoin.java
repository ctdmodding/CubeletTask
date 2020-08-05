package dev.ctdmodding.cubelettask.listener.player;

import dev.ctdmodding.cubelettask.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

/**
 * Jon created on 8/1/2020
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.getPlayer().teleport(new Location(player.getLocation().getWorld(), -8.5, 78.2, -8.5));
        if (player.getUniqueId().equals(UUID.fromString("ac5a7f86-019b-4988-9180-7586d87558da"))) { //|| player.getUniqueId().equals(UUID.fromString("77cfa6d4-46a7-4254-84ac-8cbea2264bc7"))) {
            player.setDisplayName(ChatColor.YELLOW + "[ADMIN] " + player.getName() + ChatColor.WHITE);
        } else {
            player.setDisplayName(ChatColor.DARK_AQUA + "[CUBECRAFT] " + player.getName() + ChatColor.WHITE);
        }
        event.setJoinMessage(ChatUtil.formatMessage(String.format("%s%s joined!", player.getDisplayName(), ChatColor.YELLOW)));
        player.setGameMode(GameMode.CREATIVE);
    }
}
