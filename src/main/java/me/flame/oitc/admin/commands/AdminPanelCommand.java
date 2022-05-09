package me.flame.oitc.admin.commands;

import me.flame.oitc.admin.adminpanel.gui.AdminPanelGUI;
import me.flame.oitc.admin.adminpanel.managers.AdminPanelManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminPanelCommand implements CommandExecutor {

    AdminPanelGUI adminPanelGUI = new AdminPanelGUI();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if(!p.hasPermission("oitc.admin.adminpanel")) return true;

        adminPanelGUI.openAdminPanelGUI(p.getUniqueId());

        return false;
    }
}
