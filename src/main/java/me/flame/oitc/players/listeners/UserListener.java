package me.flame.oitc.players.listeners;

import me.flame.oitc.Core;
import me.flame.oitc.admin.commands.VanishCommand;
import me.flame.oitc.players.User;
import me.flame.oitc.players.combat.CombatLogger;
import me.flame.oitc.players.commands.SpawnCommand;
import me.flame.oitc.players.kit.Kits;
import me.flame.oitc.players.levels.managers.LevelsManager;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.utils.ScoreboardUtils;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static sun.jvm.hotspot.code.CompressedStream.L;

public class UserListener implements Listener {

    UserManager userManager = new UserManager();
    ScoreboardUtils scoreboardUtils = new ScoreboardUtils();
    SpawnCommand spawnCommand = new SpawnCommand();
    LevelsManager levelsManager = new LevelsManager();
    private final Kits kits = new Kits();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        // Load user to plugin
        userManager.loadUser(uuid);
        User user = UserManager.getUser(uuid);

        // User basics
        scoreboardUtils.setScoreboard(uuid);
        spawnCommand.sendToSpawn(p.getUniqueId());
        p.setFoodLevel(20);
        p.setHealth(20);
        p.setGameMode(GameMode.ADVENTURE);

        p.setExp(0L);
        p.setLevel(user.getLevel());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        User user = UserManager.getUser(uuid);

        // Save & unload user
        userManager.saveUser(user);
        userManager.removeUser(user);

        if (CombatLogger.getInstance().getInCombat(p.getUniqueId())) {
            CombatLogger.getInstance().stopCombat(p.getUniqueId());
        }
    }

    @EventHandler
    public void breakEvent(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("oitc.build") && p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("oitc.drop") && p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntityType() != EntityType.PLAYER) return;

        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropEvent(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        if (p.hasPermission("oitc.drop") && p.getGameMode() == GameMode.CREATIVE) return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location location = p.getLocation();
        location.setY(location.getY() - 1);
        if (p.getLocation().getBlock().getType() == Material.STONE_PRESSURE_PLATE && location.getBlock().getType() == Material.RED_SANDSTONE || p.getLocation().getBlock().getType() == Material.STONE_PRESSURE_PLATE && location.getBlock().getType() == Material.SPRUCE_SLAB) {
            p.setVelocity(p.getLocation().getDirection().multiply(3).setY(1));
        }

        if (!(location.distanceSquared(p.getWorld().getSpawnLocation()) <= 180)) {
            if (p.getInventory().getHelmet() == null && p.getGameMode() == GameMode.ADVENTURE) {

                kits.giveKit(UserManager.getUser(p.getUniqueId()));

            }
        }
    }

    @EventHandler
    public void EntityHangingEvent(HangingBreakByEntityEvent e) {

        if (e.getRemover().getType() == EntityType.PLAYER) {
            Player p = (Player) e.getRemover();
            if (p.hasPermission("oitc.build") && p.getGameMode() == GameMode.CREATIVE) return;
            e.setCancelled(true);

        } else if (e.getRemover().getType() == EntityType.ARROW){
            Arrow arrow = (Arrow) e.getRemover();
            Player p = (Player) arrow.getShooter();
            if (p.hasPermission("oitc.build") && p.getGameMode() == GameMode.CREATIVE) return;
            e.setCancelled(true);
        }


    }
}
