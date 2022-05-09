package me.flame.oitc.players.settings.gui;

import me.flame.oitc.players.User;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.players.settings.Settings;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ColorSelectorGUI {

    Settings settings = new Settings();

    public Inventory colorSelectorGUI(User user) {

        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&aColor Selector"));

        inventory.setItem(10, new ItemBuilder(Material.LIME_STAINED_GLASS_PANE, 1)
                .setDisplayName("&aLime" + ((user.getArmorColor() == Color.LIME) ? " &f&o(SELECTED)" : ""))
                .setLore(""
                        , " &fUnlocked: &a✓"
                        , ""
                        , "&7&oThis is the default color for everyone.").build());

        inventory.setItem(11, new ItemBuilder(Material.PINK_STAINED_GLASS_PANE, 1)
                .setDisplayName("&dPink" + ((user.getArmorColor() == Color.PURPLE) ? " &f&o(SELECTED)" : ""))
                .setLore(""
                        , " &fUnlocked: " + (settings.hasColorPermission(user, Color.PURPLE) ? "&a✓" : "&c✘")
                        , ""
                        , "&7&oThis color can not be unlocked yet.").build());

        inventory.setItem(12, new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE, 1)
                .setDisplayName("&2Green" + ((user.getArmorColor() == Color.GREEN) ? " &f&o(SELECTED)" : ""))
                .setLore(""
                        , " &fUnlocked: " + (settings.hasColorPermission(user, Color.GREEN) ? "&a✓" : "&c✘")
                        , ""
                        , "&7&oYou get access to this color"
                        , "&7&owhen you have the &2&lVIP &7&orank."
                        , "&7&oYou can buy this on &fstore.turtlemc.nl").build());

        inventory.setItem(13, new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE, 1)
                .setDisplayName("&6Orange" + ((user.getArmorColor() == Color.ORANGE) ? " &f&o(SELECTED)" : ""))
                .setLore(""
                        , " &fUnlocked: " + (settings.hasColorPermission(user, Color.ORANGE) ? "&a✓" : "&c✘")
                        , ""
                        , "&7&oYou get access to this color"
                        , "&7&owhen you have the &6&lELITE &7&orank."
                        , "&7&oYou can buy this on &fstore.turtlemc.nl").build());

        inventory.setItem(14, new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE, 1)
                .setDisplayName("&5Purple" + ((user.getArmorColor() == Color.FUCHSIA) ? " &f&o(SELECTED)" : ""))
                .setLore(""
                        , " &fUnlocked: " + (settings.hasColorPermission(user, Color.FUCHSIA) ? "&a✓" : "&c✘")
                        , ""
                        , "&7&oYou get access to this color"
                        , "&7&owhen you have the &5&lLEGEND &7&orank."
                        , "&7&oYou can buy this on &fstore.turtlemc.nl").build());

        inventory.setItem(15, new ItemBuilder(Material.RED_STAINED_GLASS_PANE, 1)
                .setDisplayName("&4Red" + ((user.getArmorColor() == Color.MAROON) ? " &f&o(SELECTED)" : ""))
                .setLore(""
                        , " &fUnlocked: " + (settings.hasColorPermission(user, Color.MAROON) ? "&a✓" : "&c✘")
                        , ""
                        , "&7&oYou get access to this color"
                        , "&7&owhen you have the &4&lGOD &7&orank."
                        , "&7&oYou can buy this on &fstore.turtlemc.nl").build());

        inventory.setItem(16, new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE, 1)
                .setDisplayName("&3Cyan" + ((user.getArmorColor() == Color.TEAL) ? " &f&o(SELECTED)" : ""))
                .setLore(""
                        , " &fUnlocked: " + (settings.hasColorPermission(user, Color.TEAL) ? "&a✓" : "&c✘")
                        , ""
                        , "&7&oYou get access to this color"
                        , "&7&owhen you have a &3&lSTAFF &7&orank."
                        , "&7&oThis color can not be bought.").build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);

        return inventory;
    }
}
