package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.UseRoD;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoWeHaveRodLumb extends BranchTask {

    private UseRoD useRoD;
    private AreWeAtLumbBank areWeAtLumbBank;
    private OsrsOrbMaker bot;

    public DoWeHaveRodLumb(OsrsOrbMaker bot){
        this.bot=bot;
        useRoD = new UseRoD(bot);
        areWeAtLumbBank = new AreWeAtLumbBank(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.contains(2552) || Inventory.contains(2554) || Inventory.contains(2556) || Inventory.contains(2558) || Inventory.contains(2560) || Inventory.contains(2562)|| Inventory.contains(2564) || Inventory.contains(2566)
                || Equipment.contains(2552) || Equipment.contains(2554) || Equipment.contains(2556) || Equipment.contains(2558) || Equipment.contains(2560) || Equipment.contains(2562)|| Equipment.contains(2564) || Equipment.contains(2566);
    }

    @Override
    public TreeTask failureTask() {
        return areWeAtLumbBank;
    }

    @Override
    public TreeTask successTask() {
        return useRoD;
    }
}
