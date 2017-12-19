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
        Actor mob = bot.getPlayer().getTarget();
        if(mob!=null){
            mob.interact(bot.getMonster().getFinishingBlowName());
        }
        bot.getUtils().stuckCheck(16);
    }
}
