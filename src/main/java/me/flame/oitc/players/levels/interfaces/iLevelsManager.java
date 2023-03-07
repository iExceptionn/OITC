package me.flame.oitc.players.levels.interfaces;

import me.flame.oitc.players.User;

public interface iLevelsManager {

    void loadLevels();

    Double getNextUpgradeXp(User user);
}
