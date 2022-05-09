package me.flame.oitc.admin.adminpanel.managers;

import me.flame.oitc.Core;
import me.flame.oitc.admin.adminpanel.AdminPanel;
import me.flame.oitc.admin.adminpanel.interfaces.IAdminPanelManager;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AdminPanelManager implements IAdminPanelManager {

    private static ArrayList<AdminPanel> adminpanelList = new ArrayList<>();

    @Override
    public void setupAdminpanel() {

        AdminPanel adminPanel = new AdminPanel(false, 1.0, "iException", false, 1.0, "iException", false, "iException");
        adminpanelList.add(adminPanel);

        Core.getInstance().getLogger().info("Adminpanel is working.");
    }

    @Override
    public void setXPBooster(String playerName, Double booster, Boolean active) {
        getAdminPanel().setLastXPChanger(playerName);
        getAdminPanel().setXpBooster(booster);
        getAdminPanel().setXpActive(active);

        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            if(active == true){
                player.sendMessage("");
                player.sendMessage(ChatUtils.format("&a[OITC] &7There is a XP booster of &a" + booster + "&7 active now!"));
                player.sendMessage(ChatUtils.format("&a[OITC] &7Thanks to &a" + playerName + "&7."));
                player.sendMessage("");

                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
            }
        }

    }

    @Override
    public boolean isXPActive() {
        return getAdminPanel().getXpActive();
    }

    @Override
    public Double getXPBooster() {
        return getAdminPanel().getXpBooster();
    }

    @Override
    public void setCoinsBooster(String playerName, Double booster, Boolean active) {
        getAdminPanel().setLastCoinsChanger(playerName);
        getAdminPanel().setCoinsBooster(booster);
        getAdminPanel().setCoinsActive(active);

        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            if(active == true){
                player.sendMessage("");
                player.sendMessage(ChatUtils.format("&a[OITC] &7There is a Coins booster of &a" + booster + "&7 active now!"));
                player.sendMessage(ChatUtils.format("&a[OITC] &7Thanks to &a" + playerName + "&7."));
                player.sendMessage("");

                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
            }
        }

    }

    @Override
    public boolean isCoinsActive() {
        return getAdminPanel().getCoinsActive();
    }

    @Override
    public Double getCoinsBooster() {
        return getAdminPanel().getCoinsBooster();
    }

    @Override
    public void setMaxUpgrades(String playerName, Boolean active) {
        getAdminPanel().setMaxUpgradesChanger(playerName);
        getAdminPanel().setMaxUpgrades(active);

        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            if(active == true){
                player.sendMessage("");
                player.sendMessage(ChatUtils.format("&a[OITC] &7You will have temporally max upgrade items."));
                player.sendMessage(ChatUtils.format("&a[OITC] &7Thanks to &a" + playerName + "&7."));
                player.sendMessage("");

                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
            }
        }
    }

    @Override
    public boolean isMaxUpgradesActive() {
        return getAdminPanel().getMaxUpgrades();
    }

    public static AdminPanel getAdminPanel(){
        for(AdminPanel adminpanel : adminpanelList){
            return adminpanel;
        }
        return null;
    }
}
