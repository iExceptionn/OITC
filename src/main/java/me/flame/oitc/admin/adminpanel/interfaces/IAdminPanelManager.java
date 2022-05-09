package me.flame.oitc.admin.adminpanel.interfaces;

public interface IAdminPanelManager {

    void setupAdminpanel();

    void setXPBooster(String playerName, Double booster, Boolean active);

    boolean isXPActive();

    Double getXPBooster ();

    void setCoinsBooster(String playerName, Double booster, Boolean active);

    boolean isCoinsActive();

    Double getCoinsBooster();

    void setMaxUpgrades(String playerName, Boolean active);

    boolean isMaxUpgradesActive();

}
