package me.flame.oitc.players.topKills.gui;

import me.flame.oitc.Core;
import me.flame.oitc.players.User;
import me.flame.oitc.players.topKills.TopKills;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TopListKillsGUI {

    public Inventory topListKills(User user){
        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&aTop 5 Kills"));

        int place = 1;
        int inventoryplace = 11;

        for (String topKillsList2 : TopKills.sortedMap.keySet()) {
            inventory.setItem(inventoryplace, new ItemBuilder(Material.PLAYER_HEAD, 1).setDisplayName("&a&l" + place + ": &7" + topKillsList2)
                    .setLore("",
                            " &8- &7This player has &a" + TopKills.sortedMap.get(topKillsList2) + " kills&7.")
                    .setSkullOwner(topKillsList2).build());

            place++;
            inventoryplace++;
        }

        inventory.setItem(18, new ItemBuilder(Material.ARROW, 1).setDisplayName("&aGo Back").build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);
        return inventory;
    }
}
