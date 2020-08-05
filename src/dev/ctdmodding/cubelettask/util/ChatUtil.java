package dev.ctdmodding.cubelettask.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Level;

/**
 * Jon created on 7/31/2020
 */
public class ChatUtil {

    private static String prefix = colorize("&c[Cubelet]&f: ");
    private static String cubelet = "[Cubelet] ";

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void log(Level level, String log) {
        Bukkit.getLogger().log(level, cubelet + log);
    }

    public static void broadcastFormattedMessage(String message) {
        broadcastMessage(prefix + message);
    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(message);
    }

    public static void sendFormattedMessage(Player player, String message) {
        sendMessage(player, formatMessage(message));
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(message);
    }

    public static String formatMessage(String message) {
        return prefix + message;
    }
}
