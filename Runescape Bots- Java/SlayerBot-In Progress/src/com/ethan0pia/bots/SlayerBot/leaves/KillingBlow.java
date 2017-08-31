package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * Deliver killing blow.
 */
public class KillingBlow extends LeafTask {

    private GoodAssSlayerBot Bot;

    public KillingBlow(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        int task = Varbits.load(7923).getValue();
        if(Bot.player != null){
            Actor mob = Bot.player.getTarget();
            if(mob!=null){
                mob.interact(Bot.mobList.getFinishingBlowName(task));
                Execution.delayUntil(()->!mob.isValid(),1000,2500);
            }
        }

    }
}
