package me.flame.oitc.players.shop.gui;

import me.flame.oitc.players.User;
import me.flame.oitc.players.shop.manager.ShopManager;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.FileManager;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopGUI {

    ShopManager shopManager = new ShopManager();

    public void openShopGUI(User user) {

        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Inventory inventory = Bukkit.createInventory(null, 36, ChatUtils.format("&8Winkel: &d" + user.getName()));

        int cooldown = Objects.requireNonNull(FileManager.get("config.yml")).getInt("rewards.arrow-timer");
        int cooldownReduce = Objects.requireNonNull(FileManager.get("config.yml")).getInt("rewards.cooldown-reduce");


        inventory.setItem(11, new ItemBuilder(Material.ARROW, 1).setDisplayName("&aArrow Timer")
                .setLore(""
                        , "&f Huidig level: &a" + (user.getArrowLevel() >= 5 ? "&cMax Level" : user.getArrowLevel())
                        , "&f Huidige cooldown: &a" + (cooldown - (cooldownReduce * user.getArrowLevel())) + " &7(-" + cooldownReduce * user.getArrowLevel() + " seconden)"
                        , ""
                        , "&f Upgrade kosten: &a" + (user.getArrowLevel() >= 5 ? "&c0" : shopManager.getCost(ShopManager.getShop("arrow"), (user.getArrowLevel() + 1)) + " &7Coins.")
                        , ""
                        , "&7&oDoor je Arrow Timer te upgraden, krijg je een"
                        , "&7&ocooldown-reductie van &f&o15 seconden&7&o.").build());

        inventory.setItem(13, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setDisplayName("&aArmor").setLore(armorLore(user))
                .setItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());

        inventory.setItem(15, new ItemBuilder(Material.DIAMOND_SWORD, 1).setDisplayName("&aZwaard").setLore(swordLore(user))
                .setItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());

        inventory.setItem(21, new ItemBuilder(Material.EMERALD, 1).setDisplayName("&aKleuren Winkel")
                .setLore(""
                        , "&7&oHier kun je nieuwe kleuren voor je armor kopen."
                        , "&7&oDeze functie wordt binnenkort beschikbaar.").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

        inventory.setItem(23, new ItemBuilder(Material.ANVIL, 1).setDisplayName("&aKill Beloningen &c(SOON)")
                .setLore(""
                        , "&7&oHier kun je nieuwe beloningen/effecten kopen."
                        , "&7&oDeze functie wordt binnenkort beschikbaar.").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

        inventory.setContents(inventory.getContents());
        if(p != null) {
            p.openInventory(inventory);
        }
    }

    public List<String> armorLore(User user){
        List<String> list = new ArrayList<>();
        list.add(ChatUtils.format(""));
        list.add(ChatUtils.format("&f Huidig level: &a" + (user.getArmorLevel() >= 5 ? "&cMax Level" : user.getArmorLevel())));
        list.add(ChatUtils.format("&f Upgrade kosten: &a" + (user.getArmorLevel() >= 5 ? "&c0" : shopManager.getCost(ShopManager.getShop("armor"), (user.getArmorLevel() + 1)) + " &7Coins.")));
        list.add("");
        list.add(ChatUtils.format("&f Upgraded Armor:"));
        switch (user.getArmorLevel() + 1){
            case 0:
            default:
                list.add(ChatUtils.format("&7 × &fHelm: &aProtection 0"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 0"));
                list.add(ChatUtils.format("&7 × &fBroek: &aProtection 0"));
                list.add(ChatUtils.format("&7 × &fSchoenen: &aProtection 0"));

                break;
            case 1:
                list.add(ChatUtils.format("&7 × &fHelm: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 0"));
                list.add(ChatUtils.format("&7 × &fBroek: &aProtection 0"));
                list.add(ChatUtils.format("&7 × &fSchoenen: &aProtection 1"));

                break;
            case 2:
                list.add(ChatUtils.format("&7 × &fHelm: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fBroek: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fSchoenen: &aProtection 1"));

                break;
            case 3:
                list.add(ChatUtils.format("&7 × &fHelm: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fBroek: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fSchoenen: &aProtection 2"));

                break;
            case 4:
                list.add(ChatUtils.format("&7 × &fHelm: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fBroek: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fSchoenen: &aProtection 2"));

                break;
            case 5:
                list.add(ChatUtils.format("&7 × &fHelm: &aProtection 3"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fBroek: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fSchoenen: &aProtection 3"));

                break;
            case 6:
                list.add(ChatUtils.format("&7 × &fHelm: &aProtection 3"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 3"));
                list.add(ChatUtils.format("&7 × &fBroek: &aProtection 3"));
                list.add(ChatUtils.format("&7 × &fSchoenen: &aProtection 3"));

                break;

        }
        list.add(ChatUtils.format(""));
        list.add(ChatUtils.format("&7&oDoor je armor te upgraden, krijg je meer"));
        list.add(ChatUtils.format("&7&oenchantments op je pantser."));

        return list;
    }

    public List<String> swordLore(User user){
        List<String> list = new ArrayList<>();
        list.add(ChatUtils.format(""));
        list.add(ChatUtils.format("&f Huidig level: &a" + (user.getSwordLevel() >= 5 ? "&cMax Level" : user.getSwordLevel())));
        list.add(ChatUtils.format("&f Upgrade kosten: &a" + (user.getSwordLevel() >= 5 ? "&c0" : shopManager.getCost(ShopManager.getShop("sword"), (user.getSwordLevel() + 1)) + " &7Coins.")));
        list.add("");
        list.add(ChatUtils.format("&f Upgraded zwaard:"));
        switch (user.getSwordLevel() + 1){
            case 0:
            default:
                list.add(ChatUtils.format("&7 × &fType: &aStenen zwaard"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aNone"));

                break;
            case 1:
                list.add(ChatUtils.format("&7 × &fType: &aStenen zwaard"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aSharpness 1"));

                break;
            case 2:
                list.add(ChatUtils.format("&7 × &fType: &aStenen zwaard"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aSharpness 2"));

                break;
            case 3:
                list.add(ChatUtils.format("&7 × &fType: &aIjzeren zwaard"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aNone"));

                break;
            case 4:
                list.add(ChatUtils.format("&7 × &fType: &aIjzeren zwaard"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aSharpness 1"));

                break;
            case 5:
                list.add(ChatUtils.format("&7 × &fType: &aIjzeren zwaard"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aSharpness 2"));

                break;
            case 6:
                list.add(ChatUtils.format("&7 × &fType: &aDiamanten zwaard"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aNone"));

                break;

        }
        list.add(ChatUtils.format(""));
        list.add(ChatUtils.format("&7&oDoor je zwaard te upgraden, krijg je meer"));
        list.add(ChatUtils.format("&7&oenchantments en een beter sword."));

        return list;
    }
}
