package me.flame.oitc.players.settings.gui;

import me.flame.oitc.players.User;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SettingsGUI {

    UserManager userManager = new UserManager();

    public Inventory openSettingsGUI(User user) {

        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&aSettings"));

        inventory.setItem(11, new ItemBuilder(Material.ANVIL, 1).setDisplayName("&aKill Rewards")
                .setLore(""
                        , "&7&oHere you can select"
                        , "&7&oyour kill reward / effect.").build());

        inventory.setItem(13, new ItemBuilder(Material.WRITABLE_BOOK, 1).setDisplayName("&aStatistics")
                .setLore(""
                        , "&7&oHere you can open"
                        , "&7&oyour own statistic pages.").build());

        inventory.setItem(15, new ItemBuilder(Material.LIME_DYE, 1).setDisplayName("&aColor Selector")
                .setLore(""
                        , " &fCurrent color: &a" + userManager.getColorName(user.getArmorColor())
                        , ""
                        , "&7&oHere you can select"
                        , "&7&othe color of your armor."
                        , ""
                        , "&7&oYou can get these by buying a"
                        , "&7&orank or unlocking them in the shop.").build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);

        return inventory;

    }
}
