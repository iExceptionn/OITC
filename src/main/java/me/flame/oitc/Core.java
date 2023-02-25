package me.flame.oitc;

import com.zaxxer.hikari.HikariDataSource;
import me.flame.oitc.admin.adminpanel.listeners.AdminPanelInventoryListener;
import me.flame.oitc.admin.adminpanel.managers.AdminPanelManager;
import me.flame.oitc.admin.commands.AdminCoinsCommand;
import me.flame.oitc.admin.commands.AdminPanelCommand;
import me.flame.oitc.admin.commands.SetSpawnCommand;
import me.flame.oitc.players.commands.ScoreboardCommand;
import me.flame.oitc.players.commands.SpawnCommand;
import me.flame.oitc.players.killrewards.managers.KillRewardManager;
import me.flame.oitc.players.listeners.InventoryListener;
import me.flame.oitc.players.listeners.PvPEventListener;
import me.flame.oitc.players.listeners.UserListener;
import me.flame.oitc.players.managers.ArrowRespawnManager;
import me.flame.oitc.players.managers.DatabaseManager;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.players.settings.listeners.SettingsListener;
import me.flame.oitc.players.shop.manager.ShopManager;
import me.flame.oitc.players.topKills.TopList;
import me.flame.oitc.players.topKills.listeners.InventoryListenerTopKills;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.FileManager;
import me.flame.oitc.utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin implements Listener {

    private static Core instance;
    public static HikariDataSource hikari;
    private static final DatabaseManager databaseManager = new DatabaseManager();
    private static final ScoreboardUtils scoreboardUtils = new ScoreboardUtils();
    private static final UserManager userManager = new UserManager();
    private static final SpawnCommand spawnCommand = new SpawnCommand();
    private static final ShopManager shopManager = new ShopManager();
    private static final AdminPanelManager adminPanelManager = new AdminPanelManager();

    public static Core getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("TurtleMC OITC has been enabled! (Beta Version)");
        FileManager.load(this, "config.yml");

        // Database
        connectMysql();
        databaseManager.createDatabase();
        shopManager.registerShops();

        // Commands / Events / other stuff
        registerEvents();
        registerCommands();
        adminPanelManager.setupAdminpanel();
        KillRewardManager.getInstance().loadKillRewards();

        // Basic user
        TopList.getInstance().loadTopList();

        for (Player player : Bukkit.getOnlinePlayers()) {
            userManager.loadUser(player.getUniqueId());
            scoreboardUtils.setScoreboard(player.getUniqueId());
            spawnCommand.sendToSpawn(player.getUniqueId());
        }

    }

    @Override
    public void onDisable() {
        getLogger().info("TurtleMC OITC has been disabled! (Beta Version)");

        // Basic User stuff
        for (Player player : Bukkit.getOnlinePlayers()) {
            userManager.saveUser(UserManager.getUser(player.getUniqueId()));
            userManager.removeUser(UserManager.getUser(player.getUniqueId()));
            ArrowRespawnManager.getInstance().removeTimer(player.getUniqueId());
        }

        // Database
        closeConnection();
    }

    public void connectMysql() {
        hikari = new HikariDataSource();
        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");

        // Data from config
        String hostName = FileManager.get("config.yml").getString("database.host");
        String[] hostNameAndPort = hostName.split(":");
        String databaseName = FileManager.get("config.yml").getString("database.databasename");
        String databasePassword = FileManager.get("config.yml").getString("database.password");
        String databaseUser = FileManager.get("config.yml").getString("database.username");

        hikari.addDataSourceProperty("serverName", hostNameAndPort[0]);
        hikari.addDataSourceProperty("port", hostNameAndPort[1]);
        hikari.addDataSourceProperty("databaseName", databaseName);
        hikari.addDataSourceProperty("user", databaseUser);
        hikari.addDataSourceProperty("password", databasePassword);

        hikari.addDataSourceProperty("verifyServerCertificate", false);
        hikari.addDataSourceProperty("useSSL", false);


    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, this);

        pm.registerEvents(new UserListener(), this);
        pm.registerEvents(new PvPEventListener(), this);
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new AdminPanelInventoryListener(), this);
        pm.registerEvents(new SettingsListener(), this);
        pm.registerEvents(new InventoryListenerTopKills(), this);
    }

    private void registerCommands() {
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("coins").setExecutor(new AdminCoinsCommand());
        getCommand("adminpanel").setExecutor(new AdminPanelCommand());
        getCommand("scoreboard").setExecutor(new ScoreboardCommand());
    }

    private void closeConnection() {
        if (hikari != null) {
            hikari.close();
            hikari = null;
        }
    }

    public static String getPrefix() {

        return ChatUtils.format("&8[&aOITC&8]");

    }

}
