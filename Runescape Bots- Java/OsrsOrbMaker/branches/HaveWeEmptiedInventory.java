package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.DepositInventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HaveWeEmptiedInventory extends BranchTask {

    private DoesInvContain78Cosmics doesinvcontain78cosmics;
    private DepositInventory depositInventory;
    private OsrsOrbMaker bot;

    public HaveWeEmptiedInventory(OsrsOrbMaker bot){
        this.bot=bot;
        depositInventory = new DepositInventory(bot);
        doesinvcontain78cosmics = new DoesInvContain78Cosmics(bot);
    }

    @Override
    public boolean validate() {
        if(Inventory.isEmpty()){
            bot.setInventoryEmptied(true);
        }
        return bot.isInventoryEmptied();
    }

    @Override
    public TreeTask failureTask() {
        return depositInventory;
    }

    @Override
    public TreeTask successTask() {
        return doesinvcontain78cosmics;
    }
}
