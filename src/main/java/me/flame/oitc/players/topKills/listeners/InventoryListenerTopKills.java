package me.flame.oitc.players.topKills.listeners;

import me.flame.oitc.players.User;
import me.flame.oitc.players.killrewards.managers.KillRewardManager;
import me.flame.oitc.players.managers.UserManager;
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

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        Player p = (Player) e.getWhoClicked();
        User user = UserManager.getUser(p.getUniqueId());

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&aList Selector"))) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD){
                topListKillsGUI.topListKills(user);
            }
        }

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&aTop 5 Kills"))) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.ARROW){
                topListSelectorGUI.topListSelectorGUI(user);
            }
        }
    }
}
