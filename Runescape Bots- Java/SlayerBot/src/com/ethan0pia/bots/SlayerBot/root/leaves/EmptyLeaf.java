package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class EmptyLeaf extends LeafTask {

    OpiaSlayer bot;

    public EmptyLeaf(OpiaSlayer bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        bot.getUtils().stuckCheck("u");
    }
}