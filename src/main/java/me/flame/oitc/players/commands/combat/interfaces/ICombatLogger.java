package me.flame.oitc.players.commands.combat.interfaces;

import java.util.UUID;

public interface ICombatLogger {

    void startCombat(UUID uuid, Integer time);

    void stopCombat(UUID uuid);

    boolean getInCombat(UUID uuid);

    Integer getCombatTimer(UUID uuid);
}
