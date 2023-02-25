package me.flame.oitc.players.topKills;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;
import me.flame.oitc.Core;
import me.flame.oitc.players.User;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.players.topKills.interfaces.ITopList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.SortedMap;

public class TopList implements ITopList {
    final private static TopList instance = new TopList();
    public static HashMap<String, Integer> topKillsList = new HashMap<>();
    public static HashMap<String, Integer> topStreakList = new HashMap<>();
    public static HashMap<String, Integer> topDeathList = new HashMap<>();
    private final UserManager userManager = new UserManager();
    public static SortedMap<String, Integer> sortedKillsMap;
    public static SortedMap<String, Integer> sortedStreakMap;
    public static SortedMap<String, Integer> sortedDeathMap;

    @Override
    public void loadTopList() {

        topKillsList.clear();
        topStreakList.clear();
        topDeathList.clear();

        try (Connection connection = Core.hikari.getConnection()) {

            PreparedStatement topKills = connection.prepareStatement("SELECT * FROM `user_data` ORDER BY `kills` DESC LIMIT 5");
            ResultSet resultTopKills = topKills.executeQuery();

            PreparedStatement topStreak = connection.prepareStatement("SELECT * FROM `user_data` ORDER BY `bestStreak` DESC LIMIT 5");
            ResultSet resultTopStreak = topStreak.executeQuery();

            PreparedStatement topDeath = connection.prepareStatement("SELECT * FROM `user_data` ORDER BY `deaths` DESC LIMIT 5");
            ResultSet resulttopDeath = topDeath.executeQuery();

            while (resultTopKills.next()) {

                String name = resultTopKills.getString("name");
                Integer kills = resultTopKills.getInt("kills");

                topKillsList.put(name, kills);
            }

            while (resultTopStreak.next()) {

                String name = resultTopStreak.getString("name");
                Integer streak = resultTopStreak.getInt("bestStreak");

                topStreakList.put(name, streak);
            }

            while (resulttopDeath.next()) {

                String name = resulttopDeath.getString("name");
                Integer deaths = resulttopDeath.getInt("deaths");

                topDeathList.put(name, deaths);
            }

            sortedKillsMap = ImmutableSortedMap.copyOf(topKillsList, Ordering.natural().reverse().onResultOf(Functions.forMap(topKillsList)).compound(Ordering.natural()));
            sortedStreakMap = ImmutableSortedMap.copyOf(topStreakList, Ordering.natural().reverse().onResultOf(Functions.forMap(topStreakList)).compound(Ordering.natural()));
            sortedDeathMap = ImmutableSortedMap.copyOf(topDeathList, Ordering.natural().reverse().onResultOf(Functions.forMap(topDeathList)).compound(Ordering.natural()));

            Core.getInstance().getLogger().info("Top " + sortedKillsMap.size() + " kills loaded");

        } catch (Exception e) {
            e.printStackTrace();
        }

        refreshTimer();
    }

    @Override
    public void refreshTimer() {

        new BukkitRunnable() {

            @Override
            public void run() {

                for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                    User user = UserManager.getUser(online.getUniqueId());
                    userManager.saveUser(user);

                    userManager.loadUser(user.getUuid());
                    Core.getInstance().getLogger().info("I have reloaded the top 5 list's!");
                    loadTopList();
                }

                cancel();
            }
        }.runTaskLater(Core.getInstance(), 20 * 900);
    }


    public static TopList getInstance() {
        return instance;
    }
}
