package me.flame.oitc.players.topKills.gui;

import me.flame.oitc.players.User;
import me.flame.oitc.players.topKills.TopList;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TopKillstreakList {

    public Inventory topListKillstreak(User user){
        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&aTop 5 Killstreaks"));

        int place = 1;
        int inventoryplace = 11;

        for (String topStreakList2 : TopList.sortedStreakMap.keySet()) {
            inventory.setItem(inventoryplace, new ItemBuilder(Material.BOOK, place).setDisplayName("&a&l" + place + ": &7" + topStreakList2)
                    .setLore("",
                            " &8- &7This player has a &a" + TopList.sortedStreakMap.get(topStreakList2) + " killstreak&7.").build());

            place++;
            inventoryplace++;
        }

        inventory.setItem(18, new ItemBuilder(Material.ARROW, 1).setDisplayName("&aGo Back").build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);
        return inventory;
    }
}
