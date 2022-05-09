package me.flame.oitc.players.shop;

public class Shop {

    private String name;
    private Double level1;
    private Double level2;
    private Double level3;
    private Double level4;
    private Double level5;

    public Shop(String name, Double level1, Double level2, Double level3, Double level4, Double level5){
        this.name = name;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
        this.level5 = level5;
    }

    public String getName() {
        return name;
    }

    public Double getLevel1() {
        return level1;
    }

    public Double getLevel2() {
        return level2;
    }

    public Double getLevel3() {
        return level3;
    }

    public Double getLevel4() {
        return level4;
    }

    public Double getLevel5() {
        return level5;
    }
}
