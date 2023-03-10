package me.flame.oitc.players.topKills.gui;

import me.flame.oitc.players.User;
import me.flame.oitc.players.topKills.TopList;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TopLevelsList {

    public Inventory topListLevels(User user){
        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&dTop 5 Levels"));

        int place = 1;
        int inventoryplace = 11;

        for (String topLevelList : TopList.sortedLevelsMap.keySet()) {
            inventory.setItem(inventoryplace, new ItemBuilder(Material.EXPERIENCE_BOTTLE, place).setDisplayName("&d&l" + place + ": &7" + topLevelList)
                    .setLore("",
                            " &8- &7Deze speler is level &d" + TopList.sortedLevelsMap.get(topLevelList) + "&7!").build());

            place++;
            inventoryplace++;
        }

        inventory.setItem(18, new ItemBuilder(Material.ARROW, 1).setDisplayName("&dGa terug").build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);
        return inventory;
    }
}
