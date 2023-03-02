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
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&dInstellingen"));

        inventory.setItem(11, new ItemBuilder(Material.ANVIL, 1).setDisplayName("&dKill beloningen")
                .setLore(""
                        , "&7&oBekijk je kill beloningen").build());

        inventory.setItem(13, new ItemBuilder(Material.WRITABLE_BOOK, 1).setDisplayName("&dStatistieken")
                .setLore(""
                        , "&7&oBekijk hier je statistieken").build());

        inventory.setItem(15, new ItemBuilder(Material.LIME_DYE, 1).setDisplayName("&dKleurkiezer")
                .setLore(""
                        , " &fHuidige kleur: &a" + userManager.getColorName(user.getArmorColor())
                        , ""
                        , "&7&oHier kan je een andere"
                        , "&7&oleur kiezen voor je armor."
                        , ""
                        , "&7&oJe kunt deze krijgen door een rank"
                        , "&7&ote kopen of hem te unlocken.").build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);

        return inventory;

    }
}
