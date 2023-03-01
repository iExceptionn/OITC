package me.flame.oitc.players.listeners;

import me.flame.oitc.players.User;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.players.settings.gui.SettingsGUI;
import me.flame.oitc.players.shop.Shop;
import me.flame.oitc.players.shop.gui.ColorShopGUI;
import me.flame.oitc.players.shop.gui.ShopGUI;
import me.flame.oitc.players.shop.manager.ShopManager;
import me.flame.oitc.players.topKills.gui.TopListSelectorGUI;
import me.flame.oitc.utils.ChatUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

public class InventoryListener implements Listener {

    public static ShopGUI shopGUI = new ShopGUI();
    public static ColorShopGUI colorShopGUI = new ColorShopGUI();
    private ShopManager shopManager = new ShopManager();
    private SettingsGUI settingsGUI = new SettingsGUI();
    private TopListSelectorGUI topListSelectorGUI = new TopListSelectorGUI();

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e) {

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        Player p = (Player) e.getWhoClicked();
        User user = UserManager.getUser(p.getUniqueId());

        if(p.getGameMode() == GameMode.CREATIVE) return;

        if (p.getInventory().getHelmet() == null && p.getOpenInventory().getTitle().contains("Crafting")) {
            e.setCancelled(true);
            return;
        }

        if (e.getSlotType() == InventoryType.SlotType.ARMOR) { e.setCancelled(true); return; }

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&7Shop: &a"))) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.ARROW){
                Shop shop = ShopManager.getShop("arrow");
                if(shopManager.canUpgrade(user, shop, user.getArrowLevel() + 1)){
                    shopManager.upgrade(user, shop, user.getArrowLevel() + 1);
                    shopGUI.openShopGUI(user);
                    return;
                }
                p.sendMessage(ChatUtils.format("&a[OITC] &7You do not have enough coins to upgrade your &aArrow Timer&7."));
                p.closeInventory();
            }
            if(e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE){
                Shop shop = ShopManager.getShop("armor");
                if(shopManager.canUpgrade(user, shop, user.getArmorLevel() + 1)){
                    shopManager.upgrade(user, shop, user.getArmorLevel() + 1);
                    shopGUI.openShopGUI(user);
                    return;
                }
                p.sendMessage(ChatUtils.format("&a[OITC] &7You do not have enough coins to upgrade your &aArmor&7."));
                p.closeInventory();
            }
            if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD){
                Shop shop = ShopManager.getShop("sword");
                if(shopManager.canUpgrade(user, shop, user.getSwordLevel() + 1)){
                    shopManager.upgrade(user, shop, user.getSwordLevel() + 1);
                    shopGUI.openShopGUI(user);
                    return;
                }
                p.sendMessage(ChatUtils.format("&a[OITC] &7You do not have enough coins to upgrade your &aSword&7."));
                p.closeInventory();
            }
            if(e.getCurrentItem().getType() == Material.EMERALD){
                colorShopGUI.colorInventory(user);
            }
        }
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getItem() == null) return;

        if(p.getGameMode() == GameMode.ADVENTURE && p.getInventory().getHelmet() == null){
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (e.getItem().getType() == Material.EMERALD) {
                    shopGUI.openShopGUI(UserManager.getUser(p.getUniqueId()));
                }

                if(e.getItem().getType() == Material.CHEST){
                    settingsGUI.openSettingsGUI(UserManager.getUser(p.getUniqueId()));
                }

                if(e.getItem().getType() == Material.CLOCK){
                    topListSelectorGUI.topListSelectorGUI(UserManager.getUser(p.getUniqueId()));
                }
            }
        }
    }
}
