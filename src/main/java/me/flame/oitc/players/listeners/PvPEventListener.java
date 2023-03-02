package me.flame.oitc.players.listeners;

import me.flame.oitc.Core;
import me.flame.oitc.players.User;
import me.flame.oitc.players.commands.SpawnCommand;
import me.flame.oitc.players.combat.CombatLogger;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class PvPEventListener implements Listener {

    SpawnCommand spawnCommand = new SpawnCommand();
    UserManager userManager = new UserManager();
    public static HashMap<UUID, UUID> inFight = new HashMap<>();

    /**
     * Remove arrow when the arrow is on the ground or in a player.
     *
     * @param e event
     */

    @EventHandler
    public void projectileHitEvent(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getEntity();
            arrow.remove();
        }
    }

    @EventHandler
    public void entityDamageEvent(EntityDamageByEntityEvent e) {
        if (!(e.getEntityType() == EntityType.PLAYER)) return;
        Player p = (Player) e.getEntity();

        if (p.getInventory().getHelmet() == null) {
            e.setCancelled(true);
            return;
        }

        if (e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            p.setHealth(0);
            new BukkitRunnable() {
                public void run() {
                    p.spigot().respawn();
                }
            }.runTaskLater(Core.getInstance(), 5L);
            return;
        }

        if (e.getDamager() instanceof Player) {
            Player lastDamage = (Player) e.getDamager();

            CombatLogger.getInstance().startCombat(p.getUniqueId(), 30);
            CombatLogger.getInstance().startCombat(lastDamage.getUniqueId(), 30);
            return;
        }
    }

    @EventHandler
    public void onDead(PlayerDeathEvent e) {
        if (!(e.getEntityType() == EntityType.PLAYER)) return;
        Player p = e.getEntity();
        User user = UserManager.getUser(p.getUniqueId());

        if (!p.isDead()) {
            new BukkitRunnable() {
                public void run() {
                    p.spigot().respawn();
                }
            }.runTaskLater(Core.getInstance(), 5L);
        }

        e.setDeathMessage(null);
        e.setDroppedExp(0);
        e.getDrops().clear();

        if(CombatLogger.getInstance().getInCombat(p.getUniqueId())){
            CombatLogger.getInstance().stopCombat(p.getUniqueId());
        }

        if(p.getKiller() == null){
            if(inFight.containsKey(p.getUniqueId())){
                Player target = Bukkit.getServer().getPlayer(inFight.get(p.getUniqueId()));
                User TUser = UserManager.getUser(target.getUniqueId());
                inFight.remove(p.getUniqueId());
                inFight.remove(target.getUniqueId());

                if(user.getKillstreak() >= 5){
                    for(Player online : Bukkit.getServer().getOnlinePlayers()){
                        online.sendMessage(ChatUtils.format(Core.getPrefix() + "&d" + target.getName() + " &7vermoorde &d" + p.getName() + " &7met een killstreak van &f" + user.getKillstreak() + "&7!"));
                    }
                }

                userManager.removeRewards(user);
                userManager.addRewards(TUser);

                p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je bent vermoord door &d" + target.getName()));
                target.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je vermoorde &d" + p.getName()));

                return;
            }
            return;
        } else if(p.getKiller() != null){
            Player target = p.getKiller();
            User TUser = UserManager.getUser(target.getUniqueId());

            if(inFight.containsKey(p.getUniqueId())){
                inFight.remove(p.getUniqueId());
            }

            if(inFight.containsKey(target.getUniqueId())){
                inFight.remove(target.getUniqueId());
            }

            if(user.getKillstreak() >= 5){
                for(Player online : Bukkit.getServer().getOnlinePlayers()){
                    online.sendMessage(ChatUtils.format(Core.getPrefix() + "&d" + target.getName() + " &7vermoorde &d" + p.getName() + " &7met een killstreak van &f" + user.getKillstreak() + "&7!"));
                }
            }

            userManager.removeRewards(user);
            userManager.addRewards(TUser);


            p.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je bent vermoord door &d" + target.getName()));
            target.sendMessage(ChatUtils.format(Core.getPrefix() + "&7Je vermoorde &d" + p.getName()));


            return;
        }

        return;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        new BukkitRunnable() {
            public void run() {
                spawnCommand.sendToSpawn(p.getUniqueId());
            }
        }.runTaskLater(Core.getInstance(), 1L);
    }
}
