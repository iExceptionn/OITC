package me.flame.oitc.players.killrewards.gui;

import me.flame.oitc.players.User;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionType;

public class KillRewardGUI {

    public Inventory openKillRewardGUI(User user) {
        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&aKill Reward Selector"));

        inventory.setItem(12, new ItemBuilder(Material.POTION, 1).setPotionType(PotionType.SPEED, false, false)
                .setItemFlag(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES)
                .setDisplayName("&aSpeed " + (user.getKillReward().getName().equalsIgnoreCase("speed") ? "&f&o(Selected)" : ""))
                .setLore(""
                        , " &fUnlocked: &a✓"
                        , ""
                        , "&7&oYou will get the &a&oSpeed 1 &7&oeffect"
                        , "&7&ofor 5 seconds when you make a kill.").build());

        inventory.setItem(14, new ItemBuilder(Material.POTION, 1).setPotionType(PotionType.REGEN, false, false)
                .setItemFlag(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ATTRIBUTES)
                .setDisplayName("&aRegeneration " + (user.getKillReward().getName().equalsIgnoreCase("regen") ? "&f&o(Selected)" : ""))
                .setLore(""
                        , " &fUnlocked: &a✓"
                        , ""
                        , "&7&oYou will get the &a&oRegeneration 1 &7&oeffect"
                        , "&7&ofor 5 seconds when you make a kill.").build());

        inventory.setItem(18, new ItemBuilder(Material.ARROW, 1).setDisplayName("&fGo Back").build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);

        return inventory;

    }
}
