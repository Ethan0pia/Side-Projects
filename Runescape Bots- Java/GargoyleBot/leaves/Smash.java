package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class Smash extends LeafTask {

    private GargSlayer bot;

    public Smash(GargSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(bot.getPlayer() != null){
            Actor mob = bot.getPlayer().getTarget();
            if(mob!=null){
                if(mob.interact("Smash")){
                    try {
                        Execution.delayUntil(() -> !Interfaces.newQuery().containers(1490).filter(i -> i.getId() == 97648660).results().first().isVisible(), 1000);
                    } catch (Exception e) {
                        Environment.getLogger().info("Failed to deliver killing blow");
                    }
                }
            }
        }
    }
}
