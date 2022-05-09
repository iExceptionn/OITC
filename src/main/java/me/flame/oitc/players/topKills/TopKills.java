package me.flame.oitc.players.topKills;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;
import me.flame.oitc.Core;
import me.flame.oitc.players.User;
import me.flame.oitc.players.killrewards.KillReward;
import me.flame.oitc.players.killrewards.managers.KillRewardManager;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.players.settings.Settings;
import me.flame.oitc.players.topKills.interfaces.ITopKills;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.SortedMap;

public class TopKills implements ITopKills {
    private static TopKills instance = new TopKills();
    public static SortedMap<String, Integer> sortedMap;
    public static HashMap<String, Integer> topKillsList = new HashMap<>();
    private final UserManager userManager = new UserManager();

    @Override
    public void loadTopKills() {

        try (Connection connection = Core.hikari.getConnection()) {

            PreparedStatement topKills = connection.prepareStatement("SELECT * FROM `user_data` ORDER BY `kills` DESC LIMIT 5");
            ResultSet resultTopKills = topKills.executeQuery();

            while(resultTopKills.next()){

                String name = resultTopKills.getString("name");
                Integer kills = resultTopKills.getInt("kills");

                topKillsList.put(name, kills);
            }

            sortedMap = ImmutableSortedMap.copyOf(topKillsList, Ordering.natural().reverse().onResultOf(Functions.forMap(topKillsList)).compound(Ordering.natural()));
            Core.getInstance().getLogger().info("Top " + sortedMap.size() + " kills loaded");

        } catch (Exception e) {
            e.printStackTrace();
        }

        refreshTimer();
    }

    @Override
    public void refreshTimer() {

        new BukkitRunnable(){

            @Override
            public void run() {
                topKillsList.clear();

                for(Player online : Bukkit.getServer().getOnlinePlayers()){
                    User user = UserManager.getUser(online.getUniqueId());
                    userManager.saveUser(user);

                    userManager.loadUser(user.getUuid());

                    loadTopKills();
                }

                cancel();
            }
        }.runTaskLater(Core.getInstance(), 20 * 900);
    }


    public static TopKills getInstance() {
        return instance;
    }
}
