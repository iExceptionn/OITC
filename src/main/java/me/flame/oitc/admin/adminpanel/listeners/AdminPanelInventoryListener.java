package me.flame.oitc.admin.adminpanel.listeners;

import me.flame.oitc.admin.adminpanel.gui.AdminPanelConfirmGUI;
import me.flame.oitc.admin.adminpanel.gui.AdminPanelGUI;
import me.flame.oitc.admin.adminpanel.gui.AdminPanelSelectGUI;
import me.flame.oitc.admin.adminpanel.managers.AdminPanelManager;
import me.flame.oitc.utils.ChatUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminPanelInventoryListener implements Listener {

    AdminPanelSelectGUI adminPanelSelectGUI = new AdminPanelSelectGUI();
    AdminPanelManager adminPanelManager = new AdminPanelManager();
    AdminPanelGUI adminPanelGUI = new AdminPanelGUI();
    AdminPanelConfirmGUI adminPanelConfirmGUI = new AdminPanelConfirmGUI();

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e) {

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        Player p = (Player) e.getWhoClicked();

        if (p.getGameMode() == GameMode.CREATIVE) return;

        if (p.getInventory().getHelmet() == null && p.getOpenInventory().getTitle().contains("Crafting")) {
            e.setCancelled(true);
            return;
        }

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&cAdminpanel"))) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.GOLD_INGOT || e.getCurrentItem().getType() == Material.EXPERIENCE_BOTTLE) {
                adminPanelSelectGUI.ConfirmGUI(p.getUniqueId());
                return;
            }
            if(e.getCurrentItem().getType() == Material.BOOK){
                adminPanelConfirmGUI.ConfirmGUI(p.getUniqueId());
                return;
            }
        }

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&cMax Upgrades"))) {
            e.setCancelled(true);
            if(e.getCurrentItem().getType() == Material.RED_WOOL){
                adminPanelManager.setMaxUpgrades(p.getName(), false);
                adminPanelGUI.openAdminPanelGUI(p.getUniqueId());
                return;
            }
            adminPanelManager.setMaxUpgrades(p.getName(), true);
            adminPanelGUI.openAdminPanelGUI(p.getUniqueId());
        }

        if (p.getOpenInventory().getTitle().contains(ChatUtils.format("&cSelect Booster"))) {
            e.setCancelled(true);
            switch (e.getSlot()) {
                case 10:
                    adminPanelManager.setXPBooster(p.getName(), 1.0, false);
                    break;
                case 11:
                    adminPanelManager.setXPBooster(p.getName(), 1.5, true);
                    break;
                case 19:
                    adminPanelManager.setXPBooster(p.getName(), 1.6, true);
                    break;
                case 20:
                    adminPanelManager.setXPBooster(p.getName(), 1.7, true);
                    break;
                case 28:
                    adminPanelManager.setXPBooster(p.getName(), 1.8, true);
                    break;
                case 29:
                    adminPanelManager.setXPBooster(p.getName(), 1.9, true);
                    break;
                case 37:
                    adminPanelManager.setXPBooster(p.getName(), 2.0, true);
                    break;
                case 38:
                    adminPanelManager.setXPBooster(p.getName(), 2.5, true);
                    break;
                case 15:
                    adminPanelManager.setCoinsBooster(p.getName(), 1.0, false);
                    break;
                case 16:
                    adminPanelManager.setCoinsBooster(p.getName(), 1.5, true);
                    break;
                case 24:
                    adminPanelManager.setCoinsBooster(p.getName(), 1.6, true);
                    break;
                case 25:
                    adminPanelManager.setCoinsBooster(p.getName(), 1.7, true);
                    break;
                case 33:
                    adminPanelManager.setCoinsBooster(p.getName(), 1.8, true);
                    break;
                case 34:
                    adminPanelManager.setCoinsBooster(p.getName(), 1.9, true);
                    break;
                case 42:
                    adminPanelManager.setCoinsBooster(p.getName(), 2.0, true);
                    break;
                case 43:
                    adminPanelManager.setCoinsBooster(p.getName(), 2.5, true);
                    break;
                default:
                        p.sendMessage(ChatUtils.format("&a[OITC] Contact Developer, slot number:" + e.getSlot()));
                        break;
            }
            adminPanelGUI.openAdminPanelGUI(p.getUniqueId());
            return;

        }
    }
}
