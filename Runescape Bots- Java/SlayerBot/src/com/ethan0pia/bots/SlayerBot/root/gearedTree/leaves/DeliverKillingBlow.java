package com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class DeliverKillingBlow extends LeafTask {

    private OpiaSlayer bot;

    public DeliverKillingBlow(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(bot.getPlayer() != null){
            Actor mob = bot.getPlayer().getTarget();
            if(mob!=null){
                mob.interact(bot.getMonster().getFinishingBlowName());
                try {
                    Execution.delayUntil(() -> mob.getHealthGauge() == null || mob.getHealthGauge().getPercent() <= 0, 1000, 2000);
                    mob.getHealthGauge();
                }catch(Exception e){
                    Environment.getLogger().info("Failed to deliver killing blow");
                }
            }
        }
        bot.getUtils().stuckCheck("p");
    }
}
