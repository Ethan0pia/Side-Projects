package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.AttackMob;

import java.util.List;

/**
 * NOTES: done
 * 
 */
public class InCombatAtMob extends BranchTask {

    private GoodAssSlayerBot Bot;

    public InCombatAtMob(GoodAssSlayerBot bot){
        Bot=bot;
        playerhpcheck = new PlayerHPCheck(Bot);
        attackmob = new AttackMob(Bot);
    }

    private PlayerHPCheck playerhpcheck;
    private AttackMob attackmob;

    @Override
    public boolean validate() {
        if(Bot.player!=null) {
            boolean state = false;
            try{
                state = Bot.player.getTarget()==null || Bot.player.getTarget().getHealthGauge()==null || Bot.player.getTarget().getHealthGauge().getPercent()<1;
            }catch(Exception e){
                System.out.println("Failed to check if in combat.");
            }
            return state;
        }
        return true;
    }

    @Override
    public TreeTask failureTask() {
        return playerhpcheck;
    }

    @Override
    public TreeTask successTask() {
        return attackmob;
    }
}
