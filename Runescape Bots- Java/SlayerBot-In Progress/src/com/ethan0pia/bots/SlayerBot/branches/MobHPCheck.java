package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.ethan0pia.bots.SlayerBot.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.KillingBlow;

/**
 * NOTES:
 * Is monster's HP below 10%?
 */
public class MobHPCheck extends BranchTask {

    private GoodAssSlayerBot Bot;

    public MobHPCheck(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private KillingBlow killingblow = new KillingBlow(Bot);
	private EmptyLeaf empty = new EmptyLeaf();

    @Override
    public boolean validate() {
        Player player = Players.getLocal();
        if(player != null){
            if(player.getTarget().getHealthGauge().getPercent()<10){
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
        return empty;
    }

    @Override
    public TreeTask successTask() {
        return killingblow;
    }
}
