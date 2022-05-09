package me.flame.oitc.admin.adminpanel;

public class AdminPanel {

    private Boolean xpActive;
    private Double xpBooster;
    private String lastXPChanger;
    private Boolean coinsActive;
    private Double coinsBooster;
    private String lastCoinsChanger;
    private Boolean maxUpgrades;
    private String maxUpgradesChanger;

    public AdminPanel(Boolean xpActive, Double xpBooster, String lastXPChanger, Boolean coinsActive, Double coinsBooster, String lastCoinsChanger, Boolean maxUpgrades, String maxUpgradesChanger){
        this.xpActive = xpActive;
        this.xpBooster = xpBooster;
        this.lastXPChanger = lastXPChanger;
        this.coinsActive = coinsActive;
        this.coinsBooster = coinsBooster;
        this.lastCoinsChanger = lastCoinsChanger;
        this.maxUpgrades = maxUpgrades;
        this.maxUpgradesChanger = maxUpgradesChanger;
    }

    public Boolean getXpActive() {
        return xpActive;
    }

    public void setXpActive(Boolean xpActive) {
        this.xpActive = xpActive;
    }

    public Double getXpBooster() {
        return xpBooster;
    }

    public void setXpBooster(Double xpBooster) {
        this.xpBooster = xpBooster;
    }

    public String getLastXPChanger() {
        return lastXPChanger;
    }

    public void setLastXPChanger(String lastXPChanger) {
        this.lastXPChanger = lastXPChanger;
    }

    public Boolean getCoinsActive() {
        return coinsActive;
    }

    public void setCoinsActive(Boolean coinsActive) {
        this.coinsActive = coinsActive;
    }

    public Double getCoinsBooster() {
        return coinsBooster;
    }

    public void setCoinsBooster(Double coinsBooster) {
        this.coinsBooster = coinsBooster;
    }

    public String getLastCoinsChanger() {
        return lastCoinsChanger;
    }

    public void setLastCoinsChanger(String lastCoinsChanger) {
        this.lastCoinsChanger = lastCoinsChanger;
    }

    public Boolean getMaxUpgrades() {
        return maxUpgrades;
    }

    public void setMaxUpgrades(Boolean maxUpgrades) {
        this.maxUpgrades = maxUpgrades;
    }

    public String getMaxUpgradesChanger() {
        return maxUpgradesChanger;
    }

    public void setMaxUpgradesChanger(String maxUpgradesChanger) {
        this.maxUpgradesChanger = maxUpgradesChanger;
    }
}
