package dev.ctdmodding.cubelettask.cubelet.machine.menu;

import dev.ctdmodding.cubelettask.CubeletPlugin;
import dev.ctdmodding.cubelettask.cubelet.display.CubeletDisplayType;
import dev.ctdmodding.cubelettask.cubelet.display.impl.EnderChestHead;
import dev.ctdmodding.cubelettask.cubelet.entity.EntityCubeletBuilder;
import dev.ctdmodding.cubelettask.cubelet.machine.CubeletMachine;
import dev.ctdmodding.cubelettask.examples.ender.EnderCubelet;
import dev.ctdmodding.cubelettask.util.ChatUtil;
import dev.ctdmodding.cubelettask.util.ItemUtil;
import dev.ctdmodding.cubelettask.util.LocationUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CubeletMachineMenu extends GameMenu {

    private String displayName;
    private int size;

    public CubeletMachineMenu(String displayName, int size) {
        super(ItemUtil.makeCubeletMachineItem(), displayName, size);
        this.displayName = displayName;
        this.size = size;
        build();
    }

    public void build() {
        addPage(new MenuPageElement(size, displayName)
            .addButtonElement(22, new MenuButtonElement(ChatColor.RED + "Nether Cubelet", Material.NETHERRACK) {
                @Override
                public void onClick(Player whoClicked, GameMenu menu) {
                    CubeletMachine machine = CubeletPlugin.getInstance().getCubeletMachine();
                    if (machine.isCanUse()) {
                        ChatUtil.broadcastFormattedMessage(whoClicked.getDisplayName() + ChatColor.RED + " is opening a " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nether Cubelet!");
                        Location location = new Location(whoClicked.getLocation().getWorld(), -9, 79, 3);
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
                                .setInvulnerable(true);
                        EnderCubelet enderCubelet = new EnderCubelet(location, builder.getEntityCubelet());
                        enderCubelet.build();
                        machine.setCanUse(false);
                        machine.hideText();
                    } else {
                        ChatUtil.sendFormattedMessage(whoClicked, ChatUtil.colorize("&cPlease wait! The Cubelet Machine is already running."));
                    }
                    menu.close(whoClicked);
                }
            })
        );
    }
}
