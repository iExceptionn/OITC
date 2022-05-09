package me.flame.oitc.players.commands;

import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class ScoreboardCommand implements CommandExecutor {

    private final ArrayList<UUID> toggledScoreboard = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("oitc.toggle.scoreboard")) {
            if (!(sender instanceof Player)) return true;
            Player p = (Player) sender;
            if (args.length == 0) {
                if (toggledScoreboard.contains(p.getUniqueId())) {

                    p.sendMessage(ChatUtils.format("&a[OITC] &7Scoreboard &aenabled."));
                    ScoreboardUtils.setScoreboard(p.getUniqueId());
                    toggledScoreboard.remove(p.getUniqueId());
                    return true;
                }

                p.sendMessage(ChatUtils.format("&a[OITC] &7Scoreboard &cdisabled."));
                p.setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
                toggledScoreboard.add(p.getUniqueId());
                return true;
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {

                    p.sendMessage(ChatUtils.format("&a[OITC] &7Scoreboard &aenabled."));
                    ScoreboardUtils.setScoreboard(p.getUniqueId());
                    if (toggledScoreboard.contains(p.getUniqueId())) {
                        toggledScoreboard.remove(p.getUniqueId());
                    }
                    return true;

                } else if (args[0].equalsIgnoreCase("off")) {
                    p.sendMessage(ChatUtils.format("&a[OITC] &7Scoreboard &cdisabled."));
                    p.setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
                    if (!toggledScoreboard.contains(p.getUniqueId())) {
                        toggledScoreboard.add(p.getUniqueId());
                    }

                    return true;
                }
            }
        }
        sender.sendMessage(ChatUtils.format("&7You do not have the permissions for this command."));
        sender.sendMessage(ChatUtils.format("&7If you want to turn off the scoreboard buy a rank at"));
        sender.sendMessage(ChatUtils.format("&ahttp://store.turtlemc.nl/"));

        return false;
    }
}
