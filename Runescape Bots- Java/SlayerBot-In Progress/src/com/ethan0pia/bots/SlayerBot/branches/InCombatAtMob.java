package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.AttackMob;

/**
 * NOTES:
 * 
 */
public class InCombatAtMob extends BranchTask {

    private GoodAssSlayerBot Bot;

    public InCombatAtMob(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private PlayerHPCheck playerhpcheck = new PlayerHPCheck(Bot);
    private AttackMob attackmob = new AttackMob(Bot);

    @Override
    public boolean validate() {
        Player player = Players.getLocal();
        if(player!=null && player.getHealthGauge()==null){
            return true;
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return attackmob;
    }

    @Override
    public TreeTask successTask() {
        return playerhpcheck;
    }
}
