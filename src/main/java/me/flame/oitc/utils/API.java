package me.flame.oitc.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.flame.oitc.admin.adminpanel.managers.AdminPanelManager;
import org.bukkit.entity.Player;

public class API extends PlaceholderExpansion {

    AdminPanelManager adminPanelManager = new AdminPanelManager();

    @Override
    public String getIdentifier() {
        return "oitc";
    }

    @Override
    public String getAuthor() {
        return "iException";
    }

    @Override
    public String getVersion() {
        return "v1.0.0";
    }

    public String onPlaceholderRequest(Player p, String identifier){
        if(p == null){
            return null;
        }

        if(identifier.equalsIgnoreCase("xpbooster")){
            return adminPanelManager.isXPActive() ? "&aActive: &ex" + adminPanelManager.getXPBooster() : "&cDisabled";
        }

        if(identifier.equalsIgnoreCase("coinsbooster")){
            return adminPanelManager.isCoinsActive() ? "&aActive: &ex" + adminPanelManager.getCoinsBooster() : "&cDisabled";
        }

        if(identifier.equalsIgnoreCase("maxupgrades")){
            return adminPanelManager.isMaxUpgradesActive() ? "&aActive" : "&cDisabled";
        }

        return null;
    }
}
