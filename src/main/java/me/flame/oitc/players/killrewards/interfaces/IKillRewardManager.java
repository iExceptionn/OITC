package me.flame.oitc.players.killrewards.interfaces;

import me.flame.oitc.players.User;
import me.flame.oitc.players.killrewards.KillReward;

public interface IKillRewardManager {

    void loadKillRewards();

    void giveKillReward(User user, KillReward killReward);

    KillReward getKillReward(String killReward);

    String killRewardToString(KillReward killReward);
}
