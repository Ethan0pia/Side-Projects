package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.CloseBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsBankOpenGeared extends BranchTask {

    private CloseBank closebank;
    private AmIAtGargoyles amiatgargoyles;

    private GargSlayer bot;

    public IsBankOpenGeared(GargSlayer bot){
        this.bot=bot;
        closebank = new CloseBank(bot);
        amiatgargoyles = new AmIAtGargoyles(bot);
    }

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return amiatgargoyles;
    }

    @Override
    public TreeTask successTask() {
        return closebank;
    }
}
