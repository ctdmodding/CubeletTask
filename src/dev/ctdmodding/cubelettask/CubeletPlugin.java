package dev.ctdmodding.cubelettask;

import dev.ctdmodding.cubelettask.command.SpawnCommand;
import dev.ctdmodding.cubelettask.cubelet.machine.CubeletMachine;
import dev.ctdmodding.cubelettask.listener.block.BlockBreak;
import dev.ctdmodding.cubelettask.listener.block.BlockBurn;
import dev.ctdmodding.cubelettask.listener.entity.EntityCombust;
import dev.ctdmodding.cubelettask.listener.player.PlayerJoin;
import dev.ctdmodding.cubelettask.listener.player.PlayerPickup;
import dev.ctdmodding.cubelettask.util.ChatUtil;
import dev.ctdmodding.cubelettask.util.WorldUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * Jon created on 7/31/2020
 */
public class CubeletPlugin extends JavaPlugin {

    private static CubeletPlugin instance;
    private CubeletMachine cubeletMachine;

    public void onEnable() {
        instance = this;
        Bukkit.getLogger().log(Level.INFO, "Starting ctdmodding's Coding Assignment for Cubecraft Games");
        registerCommands();
        registerListeners();
        setupWorld();
    }

    public void onDisable() {

    }

    public void setupWorld() {
        World world = Bukkit.getWorld("lobby");
        WorldUtil.setupWorld(world);
        cubeletMachine = new CubeletMachine(new Location(world, -9, 79, 3));
    }

    private void registerCommands() {
        ChatUtil.log(Level.INFO, "Registering Commands");
        this.getCommand("cubelet").setExecutor(new SpawnCommand());
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EntityCombust(), this);
        pm.registerEvents(new BlockBurn(), this);
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new PlayerPickup(), this);
        pm.registerEvents(new PlayerJoin(), this);
    }

    public static CubeletPlugin getInstance() {
        return instance;
    }

    public CubeletMachine getCubeletMachine() {
        return cubeletMachine;
    }
}
