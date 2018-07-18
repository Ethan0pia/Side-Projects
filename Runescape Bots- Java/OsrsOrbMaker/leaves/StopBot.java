package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 *
 */
public class StopBot extends LeafTask {

    private OsrsOrbMaker bot;

    public StopBot(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("WaitBot");
        Environment.getLogger().severe("Could not find ring of dueling to use.");
        bot.stop("No ring of dueling.");
    }
}