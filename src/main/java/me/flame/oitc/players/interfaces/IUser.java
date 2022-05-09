package me.flame.oitc.players.interfaces;

import me.flame.oitc.players.User;

import java.util.UUID;

public interface IUser {

    void registerUser(UUID uuid);

    void loadUser (UUID uuid);

    void saveUser (User user);

    void removeUser (User user);

    void addRewards (User user);

    void removeRewards (User user);

}
