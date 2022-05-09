package me.flame.oitc.admin.adminpanel.gui;

import me.flame.oitc.admin.adminpanel.managers.AdminPanelManager;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class AdminPanelGUI {

    AdminPanelManager adminPanelManager = new AdminPanelManager();

    public Inventory openAdminPanelGUI(UUID uuid){

        Player p = Bukkit.getServer().getPlayer(uuid);
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&cAdminpanel"));

        inventory.setItem(11, new ItemBuilder(Material.EXPERIENCE_BOTTLE, 1).setDisplayName("&aXP Booster &c(SOON)")
                .setLore(""
                        , " &fActive: " + (adminPanelManager.isXPActive() == true ? "&atrue" : "&cfalse")
                        , " &fValue: &a" + adminPanelManager.getXPBooster()
                        , " &fLast changer: &a" + AdminPanelManager.getAdminPanel().getLastXPChanger()
                        , "").build());

        inventory.setItem(13, new ItemBuilder(Material.GOLD_INGOT, 1).setDisplayName("&aCoins Booster")
                .setLore(""
                        , " &fActive: " + (adminPanelManager.isCoinsActive() == true ? "&atrue" : "&cfalse")
                        , " &fValue: &a" + adminPanelManager.getCoinsBooster()
                        , " &fLast changer: &a" + AdminPanelManager.getAdminPanel().getLastCoinsChanger()
                        , "").build());

        inventory.setItem(15, new ItemBuilder(Material.BOOK, 1).setDisplayName("&aMax Upgrades")
                .setLore(""
                        , " &fActive: " + (adminPanelManager.isMaxUpgradesActive() == true ? "&atrue" : "&cfalse")
                        , " &fLast changer: &a" + AdminPanelManager.getAdminPanel().getMaxUpgradesChanger()
                        , "").build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);

        return inventory;
    }
}
