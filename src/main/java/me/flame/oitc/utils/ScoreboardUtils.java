package me.flame.oitc.utils;

import me.flame.oitc.Core;
import me.flame.oitc.admin.adminpanel.managers.AdminPanelManager;
import me.flame.oitc.players.User;
import me.flame.oitc.players.managers.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.UUID;

public class ScoreboardUtils {

    private static AdminPanelManager adminPanelManager = new AdminPanelManager();

    public static void setScoreboard(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        User user = UserManager.getUser(uuid);
        // Get player ping
        int playerPing = 0;
        try {
            Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
            playerPing = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        // Create player KDR
        DecimalFormat df = new DecimalFormat("#0.00");
        double KDRMath;
        if (user.getDeaths() == 0) {
            KDRMath = user.getKills();
        } else {
            KDRMath = user.getKills() / user.getDeaths();
        }

        String PlayerKDR = df.format(KDRMath);

        // Create scoreboard
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("noflicker", "Dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatUtils.format("&5&lVoid&d&lCraft &8| &fOITC"));

        Score cspacer3 = objective.getScore(ChatUtils.format("&3"));
        cspacer3.setScore(14);

        Score playerName = objective.getScore(ChatUtils.format("&d" + user.getName()));
        playerName.setScore(13);

        Team ping = scoreboard.registerNewTeam("ping");
        ping.addEntry(ChatUtils.format("&f- &ePing: &f"));
        ping.setSuffix((playerPing + ChatUtils.format("&7ms")));
        objective.getScore(ChatUtils.format("&f- &ePing: &f")).setScore(12);

        Team coins = scoreboard.registerNewTeam("coins");
        coins.addEntry(ChatUtils.format("&f- &eCoins: &f"));
        coins.setSuffix(df.format(user.getCoins()));
        objective.getScore(ChatUtils.format("&f- &eCoins: &f")).setScore(11);

        Score cspacer2 = objective.getScore(ChatUtils.format("&2"));
        cspacer2.setScore(9);

        Score playerStats = objective.getScore(ChatUtils.format("&dStats"));
        playerStats.setScore(8);

        Team kills = scoreboard.registerNewTeam("kills");
        kills.addEntry(ChatUtils.format("&f- &eKills: &f"));
        kills.setSuffix(String.valueOf(user.getKills()));
        objective.getScore(ChatUtils.format("&f- &eKills: &f")).setScore(7);

        Team deaths = scoreboard.registerNewTeam("deaths");
        deaths.addEntry(ChatUtils.format("&f- &eDeaths: &f"));
        deaths.setSuffix(String.valueOf(user.getDeaths()));
        objective.getScore(ChatUtils.format("&f- &eDeaths: &f")).setScore(6);

        Team kdr = scoreboard.registerNewTeam("kdr");
        kdr.addEntry(ChatUtils.format("&f- &eKDR: &f"));
        kdr.setSuffix(PlayerKDR);
        objective.getScore(ChatUtils.format("&f- &eKDR: &f")).setScore(5);

        Team killstreak = scoreboard.registerNewTeam("killstreak");
        killstreak.addEntry(ChatUtils.format("&f- &eKillstreak: &f"));
        killstreak.setSuffix(String.valueOf(user.getKillstreak()));
        objective.getScore(ChatUtils.format("&f- &eKillstreak: &f")).setScore(4);

        Team bestKillstreak = scoreboard.registerNewTeam("bestKillstreak");
        bestKillstreak.addEntry(ChatUtils.format("&f- &eBest Killstreak: &f"));
        bestKillstreak.setSuffix(String.valueOf(user.getBestKillstreak()));
        objective.getScore(ChatUtils.format("&f- &eBest Killstreak: &f")).setScore(3);

        Score cspacer1 = objective.getScore(ChatUtils.format("&1"));
        cspacer1.setScore(2);

        Score footer = objective.getScore(ChatUtils.format("&7play.voidcraft.nl"));
        footer.setScore(1);

        new BukkitRunnable() {

            @Override
            public void run() {
                int playerPing = 0;
                try {
                    Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
                    playerPing = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                    e.printStackTrace();
                }

                DecimalFormat df = new DecimalFormat("#0.00");
                double KDRMath;
                if (user.getDeaths() == 0) {
                    KDRMath = (double) user.getKills();
                } else {
                    KDRMath = (double) user.getKills() / (double) user.getDeaths();
                }
                String PlayerKDR = df.format(KDRMath);


                ping.setSuffix((playerPing + ChatUtils.format("&7ms")));
                coins.setSuffix(df.format(user.getCoins()));

                kills.setSuffix(String.valueOf(user.getKills()));
                deaths.setSuffix(String.valueOf(user.getDeaths()));
                kdr.setSuffix(PlayerKDR);
                killstreak.setSuffix(String.valueOf(user.getKillstreak()));
                bestKillstreak.setSuffix(String.valueOf(user.getBestKillstreak()));
            }
        }.runTaskTimer(Core.getInstance(), 10, 60);

        p.setScoreboard(scoreboard);
    }
}
