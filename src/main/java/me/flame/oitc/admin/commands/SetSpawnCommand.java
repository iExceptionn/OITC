package me.flame.oitc.admin.commands;

import me.flame.oitc.Core;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;
        Player p = (Player) sender;

        if(p.hasPermission("oitc.setspawn")){
            p.sendMessage(ChatUtils.format("&a[OITC] &7You have changed the spawn to your location."));

            FileManager.get("config.yml").set("spawn.location", p.getLocation().getWorld().getName()
                    + ";" + p.getLocation().getX()
                    + ";" + p.getLocation().getY()
                    + ";" + p.getLocation().getZ()
                    + ";" + p.getLocation().getYaw()
                    + ";" + p.getLocation().getPitch());

            p.getWorld().setSpawnLocation(p.getLocation());

            FileManager.save(Core.getInstance(), "config.yml");
            return true;
        }
        return false;
    }
}
