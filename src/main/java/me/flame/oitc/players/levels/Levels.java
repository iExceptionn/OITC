package me.flame.oitc.players.levels;

public class Levels {

    private int level;
    private Double xp;

    public Levels(int level, Double xp){
        this.level = level;
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Double getXp() {
        return xp;
    }

    public void setXp(Double xp) {
        this.xp = xp;
    }
}
