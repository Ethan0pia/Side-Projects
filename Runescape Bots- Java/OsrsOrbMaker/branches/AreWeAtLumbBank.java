package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.WalkLumbBank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeAtLumbBank extends BranchTask {

    private WalkLumbBank walkLumbBank;
    private IsBankOpenNotGeared isBankOpenNotGeared;
    private OsrsOrbMaker bot;
    private Area obelisk = new Area.Circular(new Coordinate(3208,3219,2),4);

    public AreWeAtLumbBank(OsrsOrbMaker bot){
        this.bot=bot;
        walkLumbBank = new WalkLumbBank(bot);
        isBankOpenNotGeared = new IsBankOpenNotGeared(bot);
    }

    @Override
    public boolean validate() {
        return obelisk.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkLumbBank;
    }

    @Override
    public TreeTask successTask() {
        return isBankOpenNotGeared;
    }
}
