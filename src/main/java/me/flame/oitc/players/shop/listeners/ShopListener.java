package me.flame.oitc.players.shop.listeners;

import me.flame.oitc.Core;
import me.flame.oitc.players.User;
import me.flame.oitc.players.killrewards.managers.KillRewardManager;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.players.settings.Settings;
import me.flame.oitc.players.shop.gui.ShopGUI;
import me.flame.oitc.players.shop.manager.ShopManager;
import me.flame.oitc.utils.ChatUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopListener implements Listener {

    private ShopManager shopManager = new ShopManager();
    private ShopGUI shopGUI = new ShopGUI();

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        Player p = (Player) e.getWhoClicked();
        User user = UserManager.getUser(p.getUniqueId());

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&7Kleuren winkel: &d"))) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.ARROW){
                shopGUI.openShopGUI(user);
                return;
            }
            if (e.getCurrentItem().getType() == Material.PINK_STAINED_GLASS_PANE) {
                if(user.getUnlockedColors().contains("pink")){
                    e.setCancelled(true);
                    return;
                }
                if (shopManager.enoughCoins(user, 225)) {
                    shopManager.unlockColor(user, "pink", 225);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt de kleur &dRoze &7gekocht."));
                } else {
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt niet genoeg &ecoins &7voor deze kleur."));
                }
                p.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.GREEN_STAINED_GLASS_PANE) {
                if(user.getUnlockedColors().contains("green")){
                    e.setCancelled(true);
                    return;
                }
                if (shopManager.enoughCoins(user, 225)) {
                    shopManager.unlockColor(user, "green", 225);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + " &7Je hebt de kleur &2Groen &7gekocht."));
                } else {
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + " &7Je hebt niet genoeg &ecoins &7voor deze kleur."));
                }
                p.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.ORANGE_STAINED_GLASS_PANE) {
                if(user.getUnlockedColors().contains("orange")){
                    e.setCancelled(true);
                    return;
                }
                if (shopManager.enoughCoins(user, 225)) {
                    shopManager.unlockColor(user, "orange", 225);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt de kleur &6Oranje &7gekocht."));
                } else {
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt niet genoeg &ecoins &7voor deze kleur."));
                }
                p.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.PURPLE_STAINED_GLASS_PANE) {
                if(user.getUnlockedColors().contains("purple")){
                    e.setCancelled(true);
                    return;
                }
                if (shopManager.enoughCoins(user, 225)) {
                    shopManager.unlockColor(user, "purple", 225);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt de kleur &5Paars &7gekocht."));
                } else {
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt niet genoeg &ecoins &7voor deze kleur."));
                }
                p.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
                if(user.getUnlockedColors().contains("red")){
                    e.setCancelled(true);
                    return;
                }
                if (shopManager.enoughCoins(user, 225)) {
                    shopManager.unlockColor(user, "red", 225);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt de kleur &4Rood &7gekocht."));
                } else {
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt niet genoeg &ecoins &7voor deze kleur."));
                }
                p.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.CYAN_STAINED_GLASS_PANE) {
                if(user.getUnlockedColors().contains("cyan")){
                    e.setCancelled(true);
                    return;
                }
                if (shopManager.enoughCoins(user, 225)) {
                    shopManager.unlockColor(user, "cyan", 225);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt de kleur &3Turquoise &7gekocht."));
                } else {
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt niet genoeg &ecoins &7voor deze kleur."));
                }
                p.closeInventory();
            }
        }
    }
}
