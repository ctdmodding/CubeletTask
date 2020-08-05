package dev.ctdmodding.cubelettask.command;

import dev.ctdmodding.cubelettask.CubeletPlugin;
import dev.ctdmodding.cubelettask.cubelet.display.CubeletDisplayType;
import dev.ctdmodding.cubelettask.cubelet.display.impl.EnderChestHead;
import dev.ctdmodding.cubelettask.cubelet.entity.EntityCubeletBuilder;
import dev.ctdmodding.cubelettask.examples.ender.EnderCubelet;
import dev.ctdmodding.cubelettask.util.LocationUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Jon created on 7/31/2020
 */
public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            CubeletPlugin.getInstance().getCubeletMachine().hideText();
            Location location = new Location(player.getLocation().getWorld(), -9, 79, 3);
            EntityCubeletBuilder builder = new EntityCubeletBuilder()
                    .setLocation(LocationUtil.getFixedLocation(location.clone()).subtract(0, 1, 0))
                    .setDisplay(new EnderChestHead())
                    .setDisplayType(CubeletDisplayType.HELMET)
                    .buildCublet()
                    .spawnArmorStand()
                    .setDisplayItem()
                    .setVisible(false)
                    .setCustomName()
                    .setCustomNameVisible(true)
                    .setGravity(false)
                    .setSmall(true)
                    .setInvulnerable(true)
                    ;
            EnderCubelet enderCubelet = new EnderCubelet(location, builder.getEntityCubelet());
            enderCubelet.build();

            player.sendMessage("Spawning Cubelet");
        }
        return false;
    }
}
