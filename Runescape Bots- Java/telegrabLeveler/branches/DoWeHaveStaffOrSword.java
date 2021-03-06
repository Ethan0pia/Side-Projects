package com.ethan0pia.bots.TelegrabLeveler.branches;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class DoWeHaveStaffOrSword extends BranchTask {

    private AreWeAtCaveEntrance areweatcaveentrance;
    private AreWeByShop arewebyshop;
    private TelegrabLeveler bot;

    public DoWeHaveStaffOrSword(TelegrabLeveler bot){
        this.bot=bot;
        arewebyshop = new AreWeByShop(bot);
        areweatcaveentrance = new AreWeAtCaveEntrance(bot);
    }


    @Override
    public boolean validate() {
        return Equipment.contains(1381) || Equipment.contains(19830);
    }

    @Override
    public TreeTask failureTask() {
        return arewebyshop;
    }

    @Override
    public TreeTask successTask() {
        return areweatcaveentrance;
    }
}
