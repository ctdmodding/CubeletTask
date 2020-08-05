package dev.ctdmodding.cubelettask.util;

import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;

import java.util.List;
import java.util.logging.Level;

/**
 * Jon created on 8/1/2020
 */
public class WorldUtil {

    public static void setupWorld(World world) {
        if (world != null) {
            clearAllEntites(world);
            clearDroppedItems(world);
            world.setTime(1000);
            world.setStorm(false);
            world.setGameRule(GameRule.DISABLE_RAIDS, true);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            world.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
            world.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, true);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            world.setGameRule(GameRule.MOB_GRIEFING, false);
            world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
        } else {
            new NullPointerException("world == null").printStackTrace();
        }
    }

    public static ArmorStand spawnArmorStand(Location location) {
        return (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
    }

    public static void clearDroppedItems(World world) {
        if (world != null) {
            List<Entity> entList = world.getEntities();
            for (Entity current : entList) {
                if (current instanceof Item) {
                    current.remove();
                }
            }
        }
    }

    public static void clearAllEntites(World world) {
        if (world != null) {
            List<Entity> entList = world.getEntities();
            for(Entity current : entList){
                if (!(current instanceof Player)) {
                    current.remove();
                }
            }
        }
    }
}
