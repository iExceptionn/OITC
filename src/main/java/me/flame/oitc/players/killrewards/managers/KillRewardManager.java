package me.flame.oitc.players.killrewards.managers;

import me.flame.oitc.players.User;
import me.flame.oitc.players.killrewards.KillReward;
import me.flame.oitc.players.killrewards.interfaces.IKillRewardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class KillRewardManager implements IKillRewardManager {

    final private static ArrayList<KillReward> killrewardsList = new ArrayList<>();
    final private static KillRewardManager instance = new KillRewardManager();
    @Override
    public void loadKillRewards() {

        KillReward killRewardRegen = new KillReward("regen", 5, 5);
        killrewardsList.add(killRewardRegen);

        KillReward killRewardSpeed = new KillReward("speed", 5, 5);
        killrewardsList.add(killRewardSpeed);



    }

    @Override
    public void giveKillReward(User user, KillReward killReward) {

        Player p = Bukkit.getServer().getPlayer(user.getUuid());

        switch (killReward.getName()){
            default:
            case "regen":
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, killReward.getTime() * 20, 1));
                break;
            case "speed":
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, killReward.getTime() * 20, 1));
                break;
        }

    }

    @Override
    public KillReward getKillReward(String killReward) {

        for(KillReward killReward1 : killrewardsList){
            if(killReward1.getName().equalsIgnoreCase(killReward.toLowerCase())){
                return killReward1;
            }
        }
        return null;
    }

    @Override
    public String killRewardToString(KillReward killReward) {

        for(KillReward killReward1 : killrewardsList){
            if(killReward == killReward1){
                return killReward1.getName().toLowerCase();
            }
        }
        return null;
    }

    public static KillRewardManager getInstance(){
        return instance;
    }
}
