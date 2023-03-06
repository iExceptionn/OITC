package me.flame.oitc.players.managers;

import me.flame.oitc.Core;
import me.flame.oitc.admin.adminpanel.managers.AdminPanelManager;
import me.flame.oitc.players.User;
import me.flame.oitc.players.interfaces.IUser;
import me.flame.oitc.players.killrewards.KillReward;
import me.flame.oitc.players.killrewards.managers.KillRewardManager;
import me.flame.oitc.players.settings.Settings;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.FileManager;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserManager implements IUser {

    public static ArrayList<User> users = new ArrayList<>();
    private ArrowRespawnManager arrowRespawnManager = new ArrowRespawnManager();
    private AdminPanelManager adminPanelManager = new AdminPanelManager();

    @Override
    public void registerUser(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        try (Connection connection = Core.hikari.getConnection()) {
            PreparedStatement insert = connection.prepareStatement("SELECT * FROM `user_data` WHERE uuid = '" + uuid + "';");
            ResultSet result = insert.executeQuery();

            if (!result.next()) {
                insert.executeUpdate("INSERT INTO `user_data` (`uuid`, `name`,`coins`, `kills`, `deaths`, `bestStreak`, `arrowLevel`, `armorLevel`, `swordLevel`) VALUE ('" + uuid + "', '" + p.getName() + "', '0', '0', '0', '0', '0', '0', '0');");
            }

        } catch (SQLException e) {
            Core.getInstance().getLogger().info("Error with creating " + p.getName() + ".");
            e.printStackTrace();
        } finally {
            Core.getInstance().getLogger().info("Player with the name " + p.getName() + " UUID: " + uuid + " has been created.");
            loadUser(uuid);
        }

    }

    @Override
    public void loadUser(UUID uuid) {
        try (Connection connection = Core.hikari.getConnection()) {
            Player p = Bukkit.getPlayer(uuid);

            PreparedStatement playerData = connection.prepareStatement("SELECT * FROM `user_data` WHERE uuid = '" + uuid + "';");
            ResultSet resultPlayerData = playerData.executeQuery();

            if (resultPlayerData.next()) {

                User user;
                String playerName = p.getName();
                double coins = resultPlayerData.getDouble("coins");
                Integer kills = resultPlayerData.getInt("kills");
                Integer deaths = resultPlayerData.getInt("deaths");
                Integer bestKillStreak = resultPlayerData.getInt("bestStreak");
                Integer arrowLevel = resultPlayerData.getInt("arrowLevel");
                Integer armorLevel = resultPlayerData.getInt("armorLevel");
                Integer swordLevel = resultPlayerData.getInt("swordLevel");
                String color = resultPlayerData.getString("color");

                Color color2 = getColor(color);
                String killRewardName = resultPlayerData.getString("killreward");
                KillReward killReward = KillRewardManager.getInstance().getKillReward(killRewardName);

                ArrayList<String> unlockedColors = new ArrayList<>();
                String unlockedColorsString = resultPlayerData.getString("bought-colors");
                String unlockedClrString[] = unlockedColorsString.split(";");
                for(String string : unlockedClrString){
                    unlockedColors.add(string);
                }

                ArrayList<String> unlockedRewards = new ArrayList<>();
                String unlockedRewardsString = resultPlayerData.getString("bought-rewards");
                String unlockedRwdString[] = unlockedRewardsString.split(";");
                for(String string : unlockedRwdString){
                    unlockedRewards.add(string);
                }

                user = new User(playerName, uuid, coins, kills, deaths, 0, bestKillStreak, arrowLevel, armorLevel, swordLevel, color2, killReward, unlockedColors, unlockedRewards);
                users.add(user);


                if(!Settings.getInstance().hasColorPermission(user, user.getArmorColor())){
                    user.setArmorColor(Color.LIME);
                }
            } else {
                registerUser(uuid);
            }
        } catch (Exception e) {
            Core.getInstance().getLogger().info("Error loading player " + Bukkit.getPlayer(uuid).getName() + ".");
            e.printStackTrace();
        } finally {
            Core.getInstance().getLogger().info("Player with the name " + Bukkit.getPlayer(uuid).getName() + " UUID: " + uuid + " has been loaded.");
        }
    }

    @Override
    public void saveUser(User user) {
        UUID uuid = user.getUuid();
        try (Connection connection = Core.hikari.getConnection()) {
            PreparedStatement playerData = connection.prepareStatement("SELECT * FROM `user_data` WHERE uuid = '" + uuid + "';");

            // Data related
            playerData.executeUpdate("UPDATE `user_data` set `name` = '" + user.getName() + "' WHERE uuid = '" + uuid + "';");
            playerData.executeUpdate("UPDATE `user_data` set `coins` = '" + user.getCoins() + "' WHERE uuid = '" + uuid + "';");

            // PvP Related
            playerData.executeUpdate("UPDATE `user_data` set `kills` = '" + user.getKills() + "' WHERE uuid = '" + uuid + "';");
            playerData.executeUpdate("UPDATE `user_data` set `deaths` = '" + user.getDeaths() + "' WHERE uuid = '" + uuid + "';");
            playerData.executeUpdate("UPDATE `user_data` set `bestStreak` = '" + user.getBestKillstreak() + "' WHERE uuid = '" + uuid + "';");

            playerData.executeUpdate("UPDATE `user_data` set `arrowLevel` = '" + user.getArrowLevel() + "' WHERE uuid = '" + uuid + "';");
            playerData.executeUpdate("UPDATE `user_data` set `armorLevel` = '" + user.getArmorLevel() + "' WHERE uuid = '" + uuid + "';");
            playerData.executeUpdate("UPDATE `user_data` set `swordLevel` = '" + user.getSwordLevel() + "' WHERE uuid = '" + uuid + "';");
            String color = getColorName(user.getArmorColor());
            playerData.executeUpdate("UPDATE `user_data` set `color` = '" + color + "' WHERE uuid = '" + uuid + "';");

            String killeffect = KillRewardManager.getInstance().killRewardToString(user.getKillReward());
            playerData.executeUpdate("UPDATE `user_data` set `killreward` = '" + killeffect + "' WHERE uuid = '" + uuid + "';");

            playerData.executeUpdate("UPDATE `user_data` set `bought-colors` = '" + user.getUnlockedColors().stream().map(Object::toString).collect(Collectors.joining(";")) + "' WHERE uuid = '" + uuid + "';");
            playerData.executeUpdate("UPDATE `user_data` set `bought-rewards` = '" + user.getUnlockedRewards().stream().map(Object::toString).collect(Collectors.joining(";")) + "' WHERE uuid = '" + uuid + "';");

            playerData.close();
        } catch (Exception e) {
            Core.getInstance().getLogger().info("Error with saving " + Bukkit.getPlayer(uuid).getName() + " UUID: " + uuid + " to the database.");
            e.printStackTrace();
        } finally {
            Core.getInstance().getLogger().info("Saved player " + Bukkit.getPlayer(uuid).getName() + " UUID: " + uuid + " to the database.");
        }
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
    }

    public static User getUser(UUID uuid) {
        for (User user : users) {
            if (user.getUuid() == uuid) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void addRewards(User user) {
        Double rewardCoinsAmount = FileManager.get("config.yml").getInt("rewards.coins-kill") * adminPanelManager.getCoinsBooster();
        Player p = Bukkit.getServer().getPlayer(user.getUuid());

        user.setKills(user.getKills() + 1);
        user.setCoins(user.getCoins() + rewardCoinsAmount);
        user.setKillstreak(user.getKillstreak() + 1);
        if (user.getKillstreak() > user.getBestKillstreak()) {
            user.setBestKillstreak(user.getKillstreak());
        }

        if(user.getKillstreak() >= 5){
            for(Player online : Bukkit.getOnlinePlayers()){
                online.sendMessage(ChatUtils.format(Core.getPrefix() + "&d" + p.getName() + " &7heeft een &dkillstreak &7van &d" + user.getKillstreak() + "&7!"));
            }
        }

        KillRewardManager.getInstance().giveKillReward(user, user.getKillReward());

        p.getInventory().addItem(new ItemBuilder(Material.ARROW, 1).setDisplayName("&fPijl").build());

    }

    @Override
    public void removeRewards(User user) {
        Integer loseCoinsAmount = FileManager.get("config.yml").getInt("rewards.coins-death");

        user.setDeaths(user.getDeaths() + 1);
        user.setCoins(user.getCoins() - loseCoinsAmount);
        user.setKillstreak(0);

    }

    public Color getColor(String input) {
        HashMap<String, Color> colors = new HashMap<>();
        colors.put("lime", Color.LIME);
        colors.put("pink", Color.FUCHSIA);
        colors.put("green", Color.GREEN);
        colors.put("orange", Color.ORANGE);
        colors.put("purple", Color.PURPLE);
        colors.put("red", Color.MAROON);
        colors.put("cyan", Color.TEAL);

        for(String color : colors.keySet()){
            if(color.contains(input.toLowerCase())){
                return colors.get(color.toLowerCase());
            }
        }

        return Color.GRAY;
    }

    public String getColorName(Color color){
        HashMap<Color, String> colors = new HashMap<>();
        colors.put(Color.LIME, "lime");
        colors.put(Color.FUCHSIA, "pink");
        colors.put(Color.GREEN, "green");
        colors.put(Color.ORANGE, "orange");
        colors.put(Color.PURPLE, "purple");
        colors.put(Color.MAROON, "red");
        colors.put(Color.TEAL, "cyan");

        for(Color colorLoop : colors.keySet()){
            if(colorLoop.equals(color)){
                return colors.get(colorLoop);
            }
        }

        return "gray";
    }
}
