package me.flame.oitc.players;

import me.flame.oitc.players.killrewards.KillReward;
import org.bukkit.Color;

import java.util.List;
import java.util.UUID;

public class User {

    private String name;
    private UUID uuid;
    private Double coins;
    private Integer kills;
    private Integer deaths;
    private Integer killstreak;
    private Integer bestKillstreak;
    private Integer arrowLevel;
    private Integer armorLevel;
    private Integer swordLevel;
    private Color armorColor;
    private KillReward killReward;

    private List<String> unlockedColors;
    private List<String> unlockedRewards;

    private Double xp;
    private int level;



    public User(String name, UUID uuid, Double coins, Integer kills, Integer deaths, Integer killstreak, Integer bestKillstreak, Integer arrowLevel, Integer armorLevel, Integer swordLevel, Color armorColor, KillReward killReward, List<String> unlockedColors, List<String> unlockedRewards, double xp, int level){
        this.name = name;
        this.uuid = uuid;
        this.coins = coins;
        this.kills = kills;
        this.deaths = deaths;
        this.killstreak = killstreak;
        this.bestKillstreak = bestKillstreak;
        this.arrowLevel = arrowLevel;
        this.armorLevel = armorLevel;
        this.swordLevel = swordLevel;
        this.armorColor = armorColor;
        this.killReward = killReward;
        this.unlockedColors = unlockedColors;
        this.unlockedRewards = unlockedRewards;
        this.xp = xp;
        this.level = level;

    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getKillstreak() {
        return killstreak;
    }

    public void setKillstreak(Integer killstreak) {
        this.killstreak = killstreak;
    }

    public Integer getBestKillstreak() {
        return bestKillstreak;
    }

    public void setBestKillstreak(Integer bestKillstreak) {
        this.bestKillstreak = bestKillstreak;
    }

    public Double getCoins() {
        return coins;
    }

    public void setCoins(Double coins) {
        this.coins = coins;
    }

    public Integer getArrowLevel() {
        return arrowLevel;
    }

    public void setArrowLevel(Integer arrowLevel) {
        this.arrowLevel = arrowLevel;
    }

    public Integer getArmorLevel() {
        return armorLevel;
    }

    public void setArmorLevel(Integer armorLevel) {
        this.armorLevel = armorLevel;
    }

    public Integer getSwordLevel() {
        return swordLevel;
    }

    public void setSwordLevel(Integer swordLevel) {
        this.swordLevel = swordLevel;
    }

    public Color getArmorColor() {
        return armorColor;
    }

    public void setArmorColor(Color armorColor) {
        this.armorColor = armorColor;
    }

    public KillReward getKillReward() {
        return killReward;
    }

    public void setKillReward(KillReward killReward) {
        this.killReward = killReward;
    }

    public List<String> getUnlockedColors() {
        return unlockedColors;
    }

    public void setUnlockedColors(List<String> unlockedColors) {
        this.unlockedColors = unlockedColors;
    }

    public List<String> getUnlockedRewards() {
        return unlockedRewards;
    }

    public void setUnlockedRewards(List<String> unlockedRewards) {
        this.unlockedRewards = unlockedRewards;
    }

    public Double getXp() {
        return xp;
    }

    public void setXp(Double xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
