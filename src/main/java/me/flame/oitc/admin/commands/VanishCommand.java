package me.flame.oitc.admin.commands;

import me.flame.oitc.Core;
import me.flame.oitc.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Array;
import java.util.ArrayList;
import java.util.UUID;

public class VanishCommand implements CommandExecutor {

    public static ArrayList<UUID> vanished = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;
        if (p.hasPermission("oitc.vanish")) {
            if (vanished.contains(p.getUniqueId())) {

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.showPlayer(Core.getInstance(), p);
                    if (player.hasPermission("oitc.vanish.see")) {
                        player.sendMessage(ChatUtils.format("&a[OITC] &7" + p.getName() + " unvanished."));
                    }
                }

                p.sendMessage(ChatUtils.format("&a[OITC] &7Unvanished."));
                p.setFlying(false);
                p.setAllowFlight(false);
                p.setGameMode(GameMode.ADVENTURE);
                vanished.remove(p.getUniqueId());
                return true;
            }

            for(Player player : Bukkit.getOnlinePlayers()){
                if(!(player.hasPermission("oitc.vanish.see"))){
                    player.hidePlayer(Core.getInstance(), p);
                } else {
                    player.sendMessage(ChatUtils.format("&a[OITC] &7" + p.getName() + " vanished."));
                }
            }
            p.sendMessage(ChatUtils.format("&a[OITC] &7Vanished."));
            p.setGameMode(GameMode.SURVIVAL);
            p.setAllowFlight(true);
            p.setFlying(true);
            vanished.add(p.getUniqueId());
        }
        return false;
    }
}
