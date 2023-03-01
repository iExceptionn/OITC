package me.flame.oitc.players.shop.interfaces;

import me.flame.oitc.players.User;
import me.flame.oitc.players.shop.Shop;

import java.awt.*;

public interface IShopManager {

    void registerShops();

    void upgrade(User user, Shop shop, Integer level);

    Double getCost(Shop shop, Integer level);

    Boolean canUpgrade(User user, Shop shop, Integer level);

    Boolean enoughCoins(User user, Integer coins);

    void unlockColor(User user, String color, Integer coins);

}
