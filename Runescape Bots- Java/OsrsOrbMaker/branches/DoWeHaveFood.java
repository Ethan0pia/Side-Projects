package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.EatFood;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.UseRoD;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoWeHaveFood extends BranchTask {

    private EatFood eatfood;
    private UseRoD useRoD;
    private OsrsOrbMaker bot;

    public DoWeHaveFood(OsrsOrbMaker bot){
        this.bot=bot;
        eatfood = new EatFood(bot);
        useRoD = new UseRoD(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.contains(329);
    }

    @Override
    public TreeTask failureTask() {
        return useRoD;
    }

    @Override
    public TreeTask successTask() {
        return eatfood;
    }
}
