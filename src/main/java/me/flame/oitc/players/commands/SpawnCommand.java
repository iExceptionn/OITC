package me.flame.oitc.players.commands;

import me.flame.oitc.players.commands.combat.CombatLogger;
import me.flame.oitc.players.managers.ArrowRespawnManager;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.FileManager;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.UUID;

public class SpawnCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        switch (args.length) {
            case 0:
            default:
                if (!(sender instanceof Player)) return true;
                Player p = (Player) sender;
                if(CombatLogger.getInstance().getInCombat(p.getUniqueId())){
                    p.sendMessage(ChatUtils.format("&a[OITC] &7You cant teleport to spawn while in combat."));
                    return true;
                }
                sendToSpawn(p.getUniqueId());
                break;
            case 1:
                if (sender.hasPermission("kitpvp.spawn.others")) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target == null) {
                        sender.sendMessage("&a[OITC] &7Player is offline or not found.");
                        break;
                    }

                    sender.sendMessage(ChatUtils.format("&a[OITC] &7You have teleported &a" + target.getName() + " &7to the spawn."));
                    target.sendMessage(ChatUtils.format("&a[OITC] &7You have been teleported to spawn by &a" + sender.getName() + "&7."));
                    sendToSpawn(target.getUniqueId());
                    break;
                }
        }
        return false;
    }

    public void sendToSpawn(UUID uuid) {
        Player p = Bukkit.getServer().getPlayer(uuid);

        ArrowRespawnManager.getInstance().removeTimer(uuid);

        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);

        for (PotionEffect potionEffect : p.getActivePotionEffects()) {
            p.removePotionEffect(potionEffect.getType());
        }

        String locatie = FileManager.get("config.yml").getString("spawn.location");
        String[] loc = locatie.split(";");
        p.teleport(new Location(Bukkit.getServer().getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5])));

        p.getInventory().setItem(4, new ItemBuilder(Material.EMERALD, 1).setDisplayName("&aShop").build());
        p.getInventory().setItem(6, new ItemBuilder(Material.CHEST, 1).setDisplayName("&aSettings").build());

    }
}
