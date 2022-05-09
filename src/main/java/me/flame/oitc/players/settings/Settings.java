package me.flame.oitc.players.settings;

import me.flame.oitc.players.User;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.players.settings.interfaces.ISettings;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

public class Settings implements ISettings {

    UserManager userManager = new UserManager();
    private static Settings instance = new Settings();

    @Override
    public void setArmorColor(User user, Color color) {

        if (hasColorPermission(user, color)) {
            user.setArmorColor(color);
        }

    }

    @Override
    public Boolean hasColorPermission(User user, Color color) {
        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        if (p.hasPermission("oitc.color." + userManager.getColorName(color)) || p.hasPermission("oitc.color.all")) {
            return true;
        }
        return false;
    }

    public static Settings getInstance(){
        return instance;
    }
}
