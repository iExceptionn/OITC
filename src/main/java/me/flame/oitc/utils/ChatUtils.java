package me.flame.oitc.utils;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String format(String input){

        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
