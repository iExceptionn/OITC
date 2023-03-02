package me.flame.oitc.players.settings.listeners;

import me.flame.oitc.Core;
import me.flame.oitc.players.User;
import me.flame.oitc.players.killrewards.managers.KillRewardManager;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.players.settings.Settings;
import me.flame.oitc.players.settings.gui.ColorSelectorGUI;
import me.flame.oitc.players.killrewards.gui.KillRewardGUI;
import me.flame.oitc.players.settings.gui.SettingsGUI;
import me.flame.oitc.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingsListener implements Listener {

    ColorSelectorGUI colorSelectorGUI = new ColorSelectorGUI();
    Settings settings = new Settings();
    KillRewardGUI killRewardGUI = new KillRewardGUI();
    SettingsGUI settingsGUI = new SettingsGUI();

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        Player p = (Player) e.getWhoClicked();
        User user = UserManager.getUser(p.getUniqueId());


        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&dInstellingen"))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.LIME_DYE) {
                colorSelectorGUI.colorSelectorGUI(user);
            }

            if(e.getCurrentItem().getType() == Material.ANVIL){
                killRewardGUI.openKillRewardGUI(user);
            }
        }

        if(p.getOpenInventory().getTitle().contains(ChatUtils.format("&dKill beloningen"))){
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.ARROW){
                settingsGUI.openSettingsGUI(user);
            }
            if(e.getSlot() == 12){
                p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt je killreward veranderd naar het &dSpeed&7 effect."));
                user.setKillReward(KillRewardManager.getInstance().getKillReward("speed"));
                settingsGUI.openSettingsGUI(user);
                return;
            }
            if(e.getSlot() == 14){
                p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt je killreward veranderd naar het &dRegeneration&7 effect."));
                user.setKillReward(KillRewardManager.getInstance().getKillReward("regen"));
                settingsGUI.openSettingsGUI(user);
                return;
            }
        }

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&dKleuren Selector"))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.LIME_STAINED_GLASS_PANE) {

                settings.setArmorColor(user, Color.LIME);
                p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt je armorkleur veranderd in &aLime&7."));
                p.closeInventory();
                return;
            }
            if (e.getCurrentItem().getType() == Material.PINK_STAINED_GLASS_PANE) {
                if (settings.hasColorPermission(user, Color.FUCHSIA)) {
                    settings.setArmorColor(user, Color.FUCHSIA);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt je armorkleur veranderd in &dPink&7."));
                    p.closeInventory();
                }
                return;
            }
            if (e.getCurrentItem().getType() == Material.GREEN_STAINED_GLASS_PANE) {
                if (settings.hasColorPermission(user, Color.GREEN)) {
                    settings.setArmorColor(user, Color.GREEN);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt je armorkleur veranderd in &2Green&7."));
                    p.closeInventory();
                }
                return;
            }
            if (e.getCurrentItem().getType() == Material.ORANGE_STAINED_GLASS_PANE) {
                if (settings.hasColorPermission(user, Color.ORANGE)) {
                    settings.setArmorColor(user, Color.ORANGE);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt je armorkleur veranderd in &6Orange&7."));
                    p.closeInventory();
                }
                return;
            }
            if (e.getCurrentItem().getType() == Material.PURPLE_STAINED_GLASS_PANE) {
                if (settings.hasColorPermission(user, Color.PURPLE)) {
                    settings.setArmorColor(user, Color.PURPLE);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt je armorkleur veranderd in &5Purple&7."));
                    p.closeInventory();
                }
                return;
            }
            if (e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
                if (settings.hasColorPermission(user, Color.MAROON)) {
                    user.setArmorColor(Color.MAROON);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt je armorkleur veranderd in &4Red&7."));
                    p.closeInventory();
                }
                return;
            }
            if (e.getCurrentItem().getType() == Material.CYAN_STAINED_GLASS_PANE) {
                if (settings.hasColorPermission(user, Color.TEAL)) {
                    user.setArmorColor(Color.TEAL);
                    p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je hebt je armorkleur veranderd in &3Cyan&7."));
                    p.closeInventory();
                }
                return;
            }
        }
    }
}
