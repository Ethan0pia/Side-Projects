package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class PayMan extends LeafTask {

    private OpiaSlayer bot;

    public PayMan(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Npc guy = Npcs.newQuery().actions("Pay").results().nearest();
        if (guy != null) {
            if (guy.interact("Pay")) {
                bot.setPaid(true);
            }
        }
        Execution.delay(1000,3000);
        bot.getUtils().stuckCheck(29);
    }
}
