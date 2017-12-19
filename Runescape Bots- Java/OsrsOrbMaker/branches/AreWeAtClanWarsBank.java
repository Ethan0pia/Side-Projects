package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.WalkBank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeAtClanWarsBank extends BranchTask {

    private IsBankOpenNotGeared isbankopennotgeared;
    private WalkBank walkbank;
    private OsrsOrbMaker bot;
    private Area bank = new Area.Circular(new Coordinate(3369,3170,0),6);

    public AreWeAtClanWarsBank(OsrsOrbMaker bot){
        this.bot=bot;
        walkbank = new WalkBank(bot);
        isbankopennotgeared = new IsBankOpenNotGeared(bot);
    }

    @Override
    public boolean validate() {
        return bank.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkbank;
    }

    @Override
    public TreeTask successTask() {
        return isbankopennotgeared;
    }
}
