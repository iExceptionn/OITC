package me.flame.oitc.players.shop.interfaces;

import me.flame.oitc.players.User;
import me.flame.oitc.players.shop.Shop;

public interface IShopManager {

    void registerShops();

    void upgrade(User user, Shop shop, Integer level);

    Double getCost(Shop shop, Integer level);

    Boolean canUpgrade(User user, Shop shop, Integer level);

}
