package me.flame.oitc.players.kit;

import me.flame.oitc.admin.adminpanel.managers.AdminPanelManager;
import me.flame.oitc.players.User;
import me.flame.oitc.players.kit.interfaces.IKits;
import me.flame.oitc.players.managers.ArrowRespawnManager;
import me.flame.oitc.utils.FileManager;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class Kits implements IKits {

    AdminPanelManager adminPanelManager = new AdminPanelManager();

    @Override
    public void giveKit(User user) {
        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        p.getInventory().clear();

        setArmor(user);
        setSword(user);
        Integer cooldown = FileManager.get("config.yml").getInt("rewards.arrow-timer");
        Integer cooldownReduce = FileManager.get("config.yml").getInt("rewards.cooldown-reduce");
        Integer level = 0;
        if(adminPanelManager.isMaxUpgradesActive()){
            level = 5;
        } else {
            level = user.getArrowLevel();
        }

        p.getInventory().setItem(1, new ItemBuilder(Material.BOW, 1).setDisplayName("&fBow").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
        p.getInventory().setItem(2, new ItemBuilder(Material.ARROW, 1).setDisplayName("&fArrow").build());

        ArrowRespawnManager.getInstance().startTimer(user.getUuid(), cooldown - (cooldownReduce * level));
    }

    @Override
    public void setArmor(User user) {
        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Color color = user.getArmorColor();

        Integer armorLevel = 0;
        if(adminPanelManager.isMaxUpgradesActive()){
            armorLevel = 5;
        } else {
            armorLevel = user.getArmorLevel();
        }

        switch(armorLevel){
            case 0:
            default:
                p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1).setColor(color).setDisplayName("&fHelmet").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                p.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).setColor(color).setDisplayName("&fChestplate").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1).setColor(color).setDisplayName("&fLeggings").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1).setColor(color).setDisplayName("&fBoots").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());

                break;
            case 1:
                p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1).setColor(color).setDisplayName("&fHelmet").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).setColor(color).setDisplayName("&fChestplate").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1).setColor(color).setDisplayName("&fLeggings").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1).setColor(color).setDisplayName("&fBoots").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).build());

                break;
            case 2:
                p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1).setColor(color).setDisplayName("&fHelmet").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).setColor(color).setDisplayName("&fChestplate").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1).setColor(color).setDisplayName("&fLeggings").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1).setColor(color).setDisplayName("&fBoots").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).build());

                break;
            case 3:
                p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1).setColor(color).setDisplayName("&fHelmet").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).setColor(color).setDisplayName("&fChestplate").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1).setColor(color).setDisplayName("&fLeggings").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(1, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1).setColor(color).setDisplayName("&fBoots").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL).build());

                break;
            case 4:
                p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1).setColor(color).setDisplayName("&fHelmet").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).setColor(color).setDisplayName("&fChestplate").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1).setColor(color).setDisplayName("&fLeggings").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1).setColor(color).setDisplayName("&fBoots").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL).build());

                break;
            case 5:
                p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET, 1).setColor(color).setDisplayName("&fHelmet").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(3, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE, 1).setColor(color).setDisplayName("&fChestplate").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS, 1).setColor(color).setDisplayName("&fLeggings").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(2, Enchantment.PROTECTION_ENVIRONMENTAL).build());
                p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS, 1).setColor(color).setDisplayName("&fBoots").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).addEnchantment(3, Enchantment.PROTECTION_ENVIRONMENTAL).build());

                break;
        }
    }

    @Override
    public void setSword(User user) {
        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Integer swordLevel = 0;
        if(adminPanelManager.isMaxUpgradesActive()){
            swordLevel = 5;
        } else {
            swordLevel = user.getSwordLevel();
        }

        switch (swordLevel) {
            case 0:
            default:
                p.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD, 1).setDisplayName("&fSword").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                break;
            case 1:
                p.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD, 1).setDisplayName("&fSword").setUnbreakable().addEnchantment(1, Enchantment.DAMAGE_ALL).setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                break;
            case 2:
                p.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD, 1).setDisplayName("&fSword").setUnbreakable().addEnchantment(2, Enchantment.DAMAGE_ALL).setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                break;
            case 3:
                p.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD, 1).setDisplayName("&fSword").setUnbreakable().setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                break;
            case 4:
                p.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD, 1).setDisplayName("&fSword").setUnbreakable().addEnchantment(1, Enchantment.DAMAGE_ALL).setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                break;
            case 5:
                p.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD, 1).setDisplayName("&fSword").setUnbreakable().addEnchantment(2, Enchantment.DAMAGE_ALL).setItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
                break;
        }
    }
}
