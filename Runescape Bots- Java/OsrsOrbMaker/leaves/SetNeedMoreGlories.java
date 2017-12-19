package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class SetNeedMoreGlories extends LeafTask {

    private OsrsOrbMaker bot;

    public SetNeedMoreGlories(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("SetNeedMoreGlories");
        bot.setGetGlories(true);
    }
}
