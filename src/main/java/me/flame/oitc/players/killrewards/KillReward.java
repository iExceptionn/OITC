package me.flame.oitc.players.killrewards;


public class KillReward {

    private String name;
    private Integer time;
    private Integer cost;

    public KillReward(String name, Integer time, Integer cost){
        this.name = name;
        this.time = time;
        this.cost = cost;
    }


    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
