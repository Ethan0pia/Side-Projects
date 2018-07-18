package com.ethan0pia.bots.CockroachKiller.branches;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.CockroachKiller.leaves.EnterCrevice;
import com.ethan0pia.bots.CockroachKiller.leaves.WalkToCoords;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeAtBank extends BranchTask {

    private IsBankOpen isBankOpen;
    private WalkToCoords walkToCoords;
    private Roach bot;
    private Area.Circular bank = new Area.Circular(new Coordinate(3094,3491,0),5);

    public AreWeAtBank(Roach bot){
        this.bot=bot;
        walkToCoords = new WalkToCoords(bot, new Coordinate(3094,3491,0));
        isBankOpen = new IsBankOpen(bot);

    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("1");
        return bank.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkToCoords;
    }

    @Override
    public TreeTask successTask() {
        return isBankOpen;
    }
}
