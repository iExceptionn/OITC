package me.flame.oitc.players.topKills.listeners;

import me.flame.oitc.players.User;
import me.flame.oitc.players.killrewards.managers.KillRewardManager;
import me.flame.oitc.players.managers.UserManager;
import me.flame.oitc.players.topKills.gui.TopDeathList;
import me.flame.oitc.players.topKills.gui.TopKillstreakList;
import me.flame.oitc.players.topKills.gui.TopListKillsGUI;
import me.flame.oitc.players.topKills.gui.TopListSelectorGUI;
import me.flame.oitc.utils.ChatUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListenerTopKills implements Listener {

    private final TopListKillsGUI topListKillsGUI = new TopListKillsGUI();
    private final TopListSelectorGUI topListSelectorGUI = new TopListSelectorGUI();
    private final TopKillstreakList topKillstreakList = new TopKillstreakList();
    private final TopDeathList topDeathList = new TopDeathList();

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        Player p = (Player) e.getWhoClicked();
        User user = UserManager.getUser(p.getUniqueId());

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&dList Selector"))) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD){
                topListKillsGUI.topListKills(user);
            }

            if(e.getCurrentItem().getType() == Material.BOOK){
                topKillstreakList.topListKillstreak(user);
            }

            if(e.getCurrentItem().getType() == Material.SKELETON_SKULL){
                topDeathList.topListDeaths(user);
            }
        }

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&dTop 5 Kills"))) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.ARROW){
                topListSelectorGUI.topListSelectorGUI(user);
            }
        }

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&dTop 5 Killstreaks"))) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.ARROW){
                topListSelectorGUI.topListSelectorGUI(user);
            }
        }

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&dTop 5 Deaths"))) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.ARROW){
                topListSelectorGUI.topListSelectorGUI(user);
            }
        }
    }
}
