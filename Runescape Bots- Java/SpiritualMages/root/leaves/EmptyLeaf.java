package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class EmptyLeaf extends LeafTask {

    OpiaSpiritualMages bot;

    public EmptyLeaf(OpiaSpiritualMages bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        bot.getUtils().stuckCheck(6);
    }
}