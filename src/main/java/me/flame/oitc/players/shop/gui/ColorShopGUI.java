package me.flame.oitc.players.shop.gui;

import me.flame.oitc.players.User;
import me.flame.oitc.players.settings.Settings;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ColorShopGUI {
    Settings settings = new Settings();

    public Inventory colorInventory(User user) {

        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&7Color-Shop: &a" + user.getName()));

        inventory.setItem(10, new ItemBuilder(Material.PINK_STAINED_GLASS_PANE, 1)
                .setDisplayName("&dPink" + ((user.getArmorColor() == Color.PURPLE) ? " &f&o(SELECTED)" : ""))
                .setLore(""
                        , " &fUnlocked: " + (settings.hasColorPermission(user, Color.PURPLE) ? "&a✓" : "&c✘")
                        , ""
                        , "&7&oThis color can not be unlocked yet.").build());

        inventory.setItem(18, new ItemBuilder(Material.ARROW, 1).setDisplayName("&aGa terug").build());

        p.openInventory(inventory);
        inventory.setContents(inventory.getContents());
        return inventory;

    }
}
