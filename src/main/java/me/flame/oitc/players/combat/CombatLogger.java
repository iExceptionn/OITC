package me.flame.oitc.players.combat;

import me.flame.oitc.Core;
import me.flame.oitc.players.combat.interfaces.ICombatLogger;
import me.flame.oitc.utils.ChatUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class CombatLogger implements ICombatLogger {

    private HashMap<UUID, Integer> combatTimer = new HashMap<>();
    private HashMap<UUID, BukkitRunnable> combatRunnable = new HashMap<>();

    private static CombatLogger instance = new CombatLogger();

    @Override
    public void startCombat(UUID uuid, Integer time) {

        if (!getInCombat(uuid)) {
            combatTimer.put(uuid, time);
            Bukkit.getServer().getPlayer(uuid).sendMessage(ChatUtils.format("&a[OITC] &7You are now in combat for &a" + time + " &7seconds."));
            combatRunnable.put(uuid, new BukkitRunnable() {
                @Override
                public void run() {

                    if(getInCombat(uuid)){
                        combatTimer.put(uuid, combatTimer.get(uuid) - 1);
                        Bukkit.getServer().getPlayer(uuid).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtils.format("&8<&a!&8> &fYou are in combat for &a" + getCombatTimer(uuid) + " &fseconds. &8<&a!&8>")));

                    }
                    if(getCombatTimer(uuid) <= 0){
                        stopCombat(uuid);
                    }

                }
            });

            combatRunnable.get(uuid).runTaskTimer(Core.getInstance(), 20, 20);
        }
        combatTimer.put(uuid, time);
    }

    @Override
    public void stopCombat(UUID uuid) {
        Player p = Bukkit.getServer().getPlayer(uuid);

        if(getInCombat(uuid)){
            combatRunnable.get(uuid).cancel();
            combatRunnable.remove(uuid);
            combatTimer.remove(uuid);
            p.sendMessage(ChatUtils.format("&a[OITC] &7You are no longer in combat."));
        }
    }

    @Override
    public boolean getInCombat(UUID uuid) {
        if(combatRunnable.containsKey(uuid)){
            return true;
        }
        return false;
    }

    @Override
    public Integer getCombatTimer(UUID uuid) {
        if(getInCombat(uuid)){
            return combatTimer.get(uuid);
        }
        return null;
    }

    public static CombatLogger getInstance(){
        return instance;
    }
}
