package me.flame.oitc.admin.adminpanel.gui;

import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class AdminPanelSelectGUI {

    public Inventory ConfirmGUI(UUID uuid){

        Player p = Bukkit.getServer().getPlayer(uuid);
        Inventory inventory = Bukkit.createInventory(null, 54, ChatUtils.format("&cSelect Booster"));

        // XP
        inventory.setItem(10, new ItemBuilder(Material.RED_WOOL, 1).setDisplayName("&cDisable XP Booster").build());
        inventory.setItem(11, new ItemBuilder(Material.EXPERIENCE_BOTTLE, 1).setDisplayName("&aEnable 1.5 XP Booster").build());
        inventory.setItem(19, new ItemBuilder(Material.EXPERIENCE_BOTTLE, 1).setDisplayName("&aEnable 1.6 XP Booster").build());
        inventory.setItem(20, new ItemBuilder(Material.EXPERIENCE_BOTTLE, 1).setDisplayName("&aEnable 1.7 XP Booster").build());
        inventory.setItem(28, new ItemBuilder(Material.EXPERIENCE_BOTTLE, 1).setDisplayName("&aEnable 1.8 XP Booster").build());
        inventory.setItem(29, new ItemBuilder(Material.EXPERIENCE_BOTTLE, 1).setDisplayName("&aEnable 1.9 XP Booster").build());
        inventory.setItem(37, new ItemBuilder(Material.EXPERIENCE_BOTTLE, 1).setDisplayName("&aEnable 2.0 XP Booster").build());
        inventory.setItem(38, new ItemBuilder(Material.EXPERIENCE_BOTTLE, 1).setDisplayName("&aEnable 2.5 XP Booster").build());

        // Gold
        inventory.setItem(15, new ItemBuilder(Material.RED_WOOL, 1).setDisplayName("&cDisable Gold Booster").build());
        inventory.setItem(16, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&aEnable 1.5 Coins Booster").build());
        inventory.setItem(24, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&aEnable 1.6 Coins Booster").build());
        inventory.setItem(25, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&aEnable 1.7 Coins Booster").build());
        inventory.setItem(33, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&aEnable 1.8 Coins Booster").build());
        inventory.setItem(34, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&aEnable 1.9 Coins Booster").build());
        inventory.setItem(42, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&aEnable 2.0 Coins Booster").build());
        inventory.setItem(43, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&aEnable 2.5 Coins Booster").build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);

        return inventory;
    }
}
