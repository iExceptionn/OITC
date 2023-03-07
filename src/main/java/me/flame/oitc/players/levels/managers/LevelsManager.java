package me.flame.oitc.players.levels.managers;

import me.flame.oitc.Core;
import me.flame.oitc.players.User;
import me.flame.oitc.players.levels.Levels;
import me.flame.oitc.players.levels.interfaces.iLevelsManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LevelsManager implements iLevelsManager {

    public static ArrayList<Levels> levelsList = new ArrayList<>();
    @Override
    public void loadLevels() {

        try (Connection connection = Core.hikari.getConnection()) {
            PreparedStatement shopData = connection.prepareStatement("SELECT * FROM `levels`");
            ResultSet resultSet = shopData.executeQuery();

            while (resultSet.next()) {
                Levels level;

                int levelNumber = resultSet.getInt("level");
                double levelCost = resultSet.getDouble("xp");

                level = new Levels(levelNumber, levelCost);
                levelsList.add(level);
            }

            shopData.close();
        } catch (SQLException e) {
            Core.getInstance().getLogger().info("Failed to load the levels.");
            e.printStackTrace();
        } finally {
            Core.getInstance().getLogger().info("I have loaded " + levelsList.size() + " levels.");
        }

    }

    @Override
    public Double getNextUpgradeXp(User user) {

        if(getLevel(user.getLevel() + 1) != null){
            return getLevel(user.getLevel() + 1).getXp();
        }

        return getLevel(user.getLevel()).getXp();
    }

    public static Levels getLevel(Integer level) {
        for(Levels levels : levelsList){
            if(levels.getLevel() == level){
                return levels;
            }
        }
        return null;
    }
}
