package me.flame.oitc.players.settings.interfaces;

import me.flame.oitc.players.User;
import org.bukkit.Color;

public interface ISettings {

    void setArmorColor(User user, Color color);

    Boolean hasColorPermission(User user, Color color);

}
