package dev.ctdmodding.cubelettask.listener.entity;

import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;

/**
 * Jon created on 8/3/2020
 */
public class EntityCombust implements Listener {

    @EventHandler
    public void onCombust(EntityCombustEvent event) {
        if (event.getEntity() instanceof Player || event.getEntity() instanceof Item || event.getEntity() instanceof FallingBlock) {
            event.setCancelled(true);
        }
    }
}
