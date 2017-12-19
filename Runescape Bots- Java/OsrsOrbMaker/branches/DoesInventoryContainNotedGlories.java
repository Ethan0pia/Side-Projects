package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.TakeGloriesFromGe;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.UseRoD;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesInventoryContainNotedGlories extends BranchTask {

    private UseRoD useRoD;
    private TakeGloriesFromGe takegloriesfromge;
    private OsrsOrbMaker bot;

    public DoesInventoryContainNotedGlories(OsrsOrbMaker bot){
        this.bot=bot;
        useRoD = new UseRoD(bot);
        takegloriesfromge = new TakeGloriesFromGe(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.contains(11979);
    }

    @Override
    public TreeTask failureTask() {
        return takegloriesfromge;
    }

    @Override
    public TreeTask successTask() {
        return useRoD;
    }
}
