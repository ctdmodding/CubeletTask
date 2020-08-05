package dev.ctdmodding.cubelettask.listener.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

/**
 * Jon created on 8/5/2020
 */
public class BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!event.getPlayer().getUniqueId().equals(UUID.fromString("ac5a7f86-019b-4988-9180-7586d87558da"))) {
            event.setCancelled(true);
        }
    }
}
