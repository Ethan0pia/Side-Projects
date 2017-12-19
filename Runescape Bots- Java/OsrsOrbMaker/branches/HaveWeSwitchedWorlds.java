package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.SwitchWorld;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class HaveWeSwitchedWorlds extends BranchTask {

    private DoWeHaveRodLumb doWeHaveRodLumb;
    private SwitchWorld switchworld;
    private OsrsOrbMaker bot;

    public HaveWeSwitchedWorlds(OsrsOrbMaker bot){
        this.bot=bot;
        doWeHaveRodLumb = new DoWeHaveRodLumb(bot);
        switchworld = new SwitchWorld(bot);
    }

    @Override
    public boolean validate() {
        bot.setBeenInPortal(true);
        return bot.isSwitchedWorlds();
    }

    @Override
    public TreeTask failureTask() {
        return switchworld;
    }

    @Override
    public TreeTask successTask() {
        return doWeHaveRodLumb;
    }
}
