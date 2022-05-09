package me.flame.oitc.players.managers;

import me.flame.oitc.Core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    public void createDatabase(){
        try{
            Connection connection = Core.hikari.getConnection();
            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `user_data` (`uuid` varchar(36) NOT NULL, `name` varchar(36) NOT NULL, `coins` DOUBLE NOT NULL , `kills` INT NOT NULL, `deaths` INT NOT NULL, `bestStreak` INT NOT NULL, `arrowLevel` INT NOT NULL, `armorLevel` INT NOT NULL, `swordLevel` INT NOT NULL, `color` varchar(36) NOT NULL, PRIMARY KEY (`uuid`))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `shop_data`(`name` varchar(36) NOT NULL, `level1` DOUBLE NOT NULL, `level2` DOUBLE NOT NULL, `level3` DOUBLE NOT NULL, `level4` DOUBLE NOT NULL, `level5` DOUBLE NOT NULL, PRIMARY KEY (`name`))");

        } catch (SQLException e){
            Core.getInstance().getLogger().info("Failed to connect to a database, plugin disabled.");
            e.printStackTrace();

            Core.getInstance().getPluginLoader().disablePlugin(Core.getInstance());
        }
    }
}
