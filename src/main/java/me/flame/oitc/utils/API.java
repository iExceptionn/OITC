package me.flame.oitc.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.flame.oitc.admin.adminpanel.managers.AdminPanelManager;
import me.flame.oitc.players.managers.UserManager;
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
            return adminPanelManager.isXPActive() ? "&aActief: &ex" + adminPanelManager.getXPBooster() : "&cNiet actief";
        }

        if(identifier.equalsIgnoreCase("coinsbooster")){
            return adminPanelManager.isCoinsActive() ? "&aActief: &ex" + adminPanelManager.getCoinsBooster() : "&cNiet actief";
        }

        if(identifier.equalsIgnoreCase("maxupgrades")){
            return adminPanelManager.isMaxUpgradesActive() ? "&aActief" : "&cNiet actief";
        }

        if(identifier.equalsIgnoreCase("getplayerlevel")){
            return String.valueOf(UserManager.getUser(p.getUniqueId()).getLevel());
        }

        return null;
    }
}
