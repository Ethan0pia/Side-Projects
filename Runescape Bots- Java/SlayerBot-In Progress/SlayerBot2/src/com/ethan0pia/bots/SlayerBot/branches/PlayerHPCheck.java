package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.EatFood;

/**
 * NOTES: done
 * Checks if player HP is higher than X% from GUI.
 */
public class PlayerHPCheck extends BranchTask {

    private EatFood eatfood = new EatFood();
    private RequiresBlow requiresblow = new RequiresBlow();
    private Player player;

    @Override
    public boolean validate() {
        player= Players.getLocal();
        if(player!=null){
            if(player.getHealthGauge().getPercent()<30){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return requiresblow;
    }

    @Override
    public TreeTask successTask() {
        return eatfood;
    }
}
