package me.flame.oitc.players.managers;

import me.flame.oitc.Core;
import me.flame.oitc.players.combat.CombatLogger;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.ItemBuilder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class ArrowRespawnManager {

    private static final HashMap<UUID, Integer> arrowList = new HashMap<>();
    private static final HashMap<UUID, BukkitRunnable> runnable = new HashMap<>();

    private static ArrowRespawnManager instance = new ArrowRespawnManager();

    public void startTimer(UUID uuid, Integer timer) {
        Player p = Bukkit.getServer().getPlayer(uuid);
        if (!hasTimer(uuid)) {
            arrowList.put(uuid, timer);
            if (!CombatLogger.getInstance().getInCombat(p.getUniqueId())) {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtils.format("&fJe krijgt een nieuwe pijl over &d" + arrowTimer(uuid) + " &fseconden.")));
            }
            runnable.put(uuid, new BukkitRunnable() {
                @Override
                public void run() {
                    if (hasTimer(uuid)) {
                        arrowList.put(uuid, arrowTimer(uuid) - 1);
                        if (!CombatLogger.getInstance().getInCombat(p.getUniqueId())) {
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtils.format("&fJe krijgt een nieuwe pijl over &d" + arrowTimer(uuid) + " &fseconden.")));
                        }
                    }
                    if (arrowTimer(uuid) == 0) {
                        arrowList.put(uuid, timer);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtils.format("&dSucces met je nieuwe pijl.")));

                        p.getInventory().addItem(new ItemBuilder(Material.ARROW, 1).setDisplayName("&fPijl").build());
                    }
                }
            });

            runnable.get(uuid).runTaskTimer(Core.getInstance(), 20, 20);
        }
        arrowList.put(uuid, timer);
    }

    public void removeTimer(UUID uuid) {
        if (hasTimer(uuid)) {
            runnable.get(uuid).cancel();
            runnable.remove(uuid);
            arrowList.remove(uuid);
        }
    }

    public boolean hasTimer(UUID uuid) {
        return arrowList.containsKey(uuid);
    }

    public Integer arrowTimer(UUID uuid) {
        if (hasTimer(uuid)) {
            return arrowList.get(uuid);
        }
        return null;
    }

    public static ArrowRespawnManager getInstance() {
        return instance;
    }
}
