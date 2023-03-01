package me.flame.oitc.players.shop.manager;

import me.flame.oitc.Core;
import me.flame.oitc.players.User;
import me.flame.oitc.players.shop.Shop;
import me.flame.oitc.players.shop.interfaces.IShopManager;
import me.flame.oitc.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ShopManager implements IShopManager {

    private static ArrayList<Shop> shopList = new ArrayList<>();

    @Override
    public void registerShops() {
        try(Connection connection = Core.hikari.getConnection()){
            PreparedStatement shopData = connection.prepareStatement("SELECT * FROM `shop_data`");
            ResultSet resultSet = shopData.executeQuery();

            while(resultSet.next()){
                Shop shop;

                String name = resultSet.getString("name");
                double level1 = resultSet.getDouble("level1");
                double level2 = resultSet.getDouble("level2");
                double level3 = resultSet.getDouble("level3");
                double level4 = resultSet.getDouble("level4");
                double level5 = resultSet.getDouble("level5");

                shop = new Shop(name, level1, level2, level3, level4, level5);
                shopList.add(shop);
            }

            shopData.close();
        } catch (SQLException e){
            Core.getInstance().getLogger().info("Failed to load the shop.");
            e.printStackTrace();
        } finally {
            Core.getInstance().getLogger().info("I have loaded " + shopList.size() + " shop upgrades.");
        }
    }

    @Override
    public void upgrade(User user, Shop shop, Integer level) {
        Player p = Bukkit.getServer().getPlayer(user.getUuid());

        switch (shop.getName().toLowerCase()){
            case "arrow":
                user.setArrowLevel(level);
                user.setCoins(user.getCoins() - getCost(shop, level));
                p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt de &dArrow Timer &7geupgrade naar level &a" + level + "&7."));
                break;
            case "armor":
                user.setArmorLevel(level);
                user.setCoins(user.getCoins() - getCost(shop, level));
                p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7e hebt je &dArmor &7geupgrade naar &a" + level + "&7."));
                break;
            case "sword":
                user.setSwordLevel(level);
                user.setCoins(user.getCoins() - getCost(shop, level));
                p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt je &dSword &7geupgrade naar &a" + level + "&7."));
                break;
        }

        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);

    }

    @Override
    public Double getCost(Shop shop, Integer level) {

        switch (level){
            case 1:
            default:
                return shop.getLevel1();
            case 2:
                return shop.getLevel2();
            case 3:
                return shop.getLevel3();
            case 4:
                return shop.getLevel4();
            case 5:
                return shop.getLevel5();
        }
    }

    @Override
    public Boolean canUpgrade(User user, Shop shop, Integer level) {
        if(level > 5){
            return false;
        }

        if(user.getCoins() >= getCost(shop, level)){
            return true;
        }
        return false;
    }

    @Override
    public Boolean enoughCoins(User user, Integer coins) {
        if(user.getCoins() >= coins){
            return true;
        }
        return false;
    }

    @Override
    public void unlockColor(User user, String color, Integer coins) {

        if(enoughCoins(user, coins)){
            if(!user.getUnlockedColors().contains(color)){
                List<String> unlockColor = user.getUnlockedColors();
                unlockColor.add(color);
                user.setUnlockedColors(unlockColor);
                user.setCoins(user.getCoins() - coins);
            }
        }

    }

    public static Shop getShop(String name) {
        for(Shop shop : shopList){
            if(shop.getName().equalsIgnoreCase(name)){
                return shop;
            }
        }
        return null;
    }
}
