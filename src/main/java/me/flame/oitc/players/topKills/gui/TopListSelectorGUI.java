package me.flame.oitc.players.topKills.gui;

import me.flame.oitc.players.User;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TopListSelectorGUI {

    public Inventory topListSelectorGUI(User user){

        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&dList Selector"));

        inventory.setItem(10, new ItemBuilder(Material.DIAMOND_SWORD, 1).setDisplayName("&dTop 5 Kills").build());

        inventory.setItem(12, new ItemBuilder(Material.BOOK, 1).setDisplayName("&dTop 5 Killstreak").build());

        inventory.setItem(14, new ItemBuilder(Material.SKELETON_SKULL, 1).setDisplayName("&dTop 5 Deaths").build());

        inventory.setItem(16, new ItemBuilder(Material.EXPERIENCE_BOTTLE, 1).setDisplayName("&dTop 5 Levels").build());


        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);

        return inventory;
    }
}
