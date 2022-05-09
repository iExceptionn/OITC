package me.flame.oitc.admin.adminpanel.gui;

import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class AdminPanelConfirmGUI {

    public Inventory ConfirmGUI(UUID uuid){

        Player p = Bukkit.getServer().getPlayer(uuid);
        Inventory inventory = Bukkit.createInventory(null, 9, ChatUtils.format("&cMax Upgrades"));

        inventory.setItem(3, new ItemBuilder(Material.RED_WOOL, 1).setDisplayName("&cDisable Max Upgrades").build());
        inventory.setItem(5, new ItemBuilder(Material.GREEN_WOOL, 1).setDisplayName("&aEnable Max Upgrades").build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);

        return inventory;
    }
}
