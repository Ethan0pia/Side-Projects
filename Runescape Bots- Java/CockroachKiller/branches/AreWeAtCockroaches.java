package com.ethan0pia.bots.CockroachKiller.branches;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.CockroachKiller.leaves.EnterCrevice;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeAtCockroaches extends BranchTask {

    private AreWeByCrevice areWeByCrevice;
    private DoWeNeedToEat doWeNeedToEat;
    private Roach bot;
    private Area.Rectangular mobArea = new Area.Rectangular(new Coordinate(3145,4273,3),new Coordinate(3160,4281,3));

    public AreWeAtCockroaches(Roach bot){
        this.bot=bot;
        doWeNeedToEat = new DoWeNeedToEat(bot);
        areWeByCrevice = new AreWeByCrevice(bot);

    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("2");
        return mobArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return areWeByCrevice;
    }

    @Override
    public TreeTask successTask() {
        return doWeNeedToEat;
    }
}
