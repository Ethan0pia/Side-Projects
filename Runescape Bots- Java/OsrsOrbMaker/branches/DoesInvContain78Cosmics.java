package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.Withdraw78Cosmics;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesInvContain78Cosmics extends BranchTask {

    private DoesInventoryContainFood doesinventorycontainfood;
    private Withdraw78Cosmics withdraw78cosmics;
    private OsrsOrbMaker bot;

    public DoesInvContain78Cosmics(OsrsOrbMaker bot){
        this.bot=bot;
        doesinventorycontainfood = new DoesInventoryContainFood(bot);
        withdraw78cosmics = new Withdraw78Cosmics(bot);
    }

    @Override
    public boolean validate() {
        SpriteItem cosmics = Inventory.newQuery().ids(564).results().first();
        return cosmics!=null && cosmics.getQuantity()>=78;
    }

    @Override
    public TreeTask failureTask() {
        return withdraw78cosmics;
    }

    @Override
    public TreeTask successTask() {
        return doesinventorycontainfood;
    }
}
