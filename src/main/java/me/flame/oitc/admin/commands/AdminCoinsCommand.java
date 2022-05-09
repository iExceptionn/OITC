package me.flame.oitc.admin.commands;

import me.flame.oitc.players.User;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("oitc.admin.coins")) return true;

        switch (args.length) {
            case 0:
            case 2:
            default:
                sender.sendMessage(ChatUtils.format(""));
                sender.sendMessage(ChatUtils.format("&a[OITC] &7/coins <player> give <amount>"));
                sender.sendMessage(ChatUtils.format("&a[OITC] &7/coins <player> set <amount>"));
                sender.sendMessage(ChatUtils.format("&a[OITC] &7/coins <player> remove <amount>"));
                sender.sendMessage(ChatUtils.format(""));
                break;
            case 1:
                Player player = Bukkit.getServer().getPlayer(args[0]);
                if (player == null) {
                    sender.sendMessage(ChatUtils.format("&a[OITC] &7Player is offline or doens't exist."));
                    break;
                }
                sender.sendMessage(ChatUtils.format(""));
                sender.sendMessage(ChatUtils.format("&a[OITC] &7/coins <player> give <amount>"));
                sender.sendMessage(ChatUtils.format("&a[OITC] &7/coins <player> set <amount>"));
                sender.sendMessage(ChatUtils.format("&a[OITC] &7/coins <player> remove <amount>"));
                sender.sendMessage(ChatUtils.format(""));
                break;
            case 3:
                Player p = Bukkit.getServer().getPlayer(args[0]);
                User user = UserManager.getUser(p.getUniqueId());
                Double amount = Double.valueOf(args[2]);
                switch (args[1].toLowerCase()) {
                    default:
                        sender.sendMessage(ChatUtils.format("&a[OITC] &7Invalid amount."));
                        break;
                    case "give":
                        user.setCoins(user.getCoins() + amount);
                        sender.sendMessage(ChatUtils.format("&a[OITC] &7You have given &a" + p.getName() + " &7a amount of &a" + amount + " &7Coins."));
                        break;
                    case "set":
                        user.setCoins(amount);
                        sender.sendMessage(ChatUtils.format("&a[OITC] &7You have changed &a" + p.getName() + " &7amount of Coins to &a" + amount + "&7."));
                        break;
                    case "remove":
                        if (user.getCoins() - amount <= 0) {
                            user.setCoins(0.0);
                            sender.sendMessage(ChatUtils.format("&a[OITC] &7You have changed &a" + p.getName() + " &7amount of Coins to &a0&7."));
                            break;
                        }
                        user.setCoins(user.getCoins() - amount);
                        sender.sendMessage(ChatUtils.format("&a[OITC] &7You have removed &a" + amount + " &7Coins from &a" + p.getName() + "&7."));
                        break;
                }
        }
        return false;
    }
}
