package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.CloseBank;
import com.ethan0pia.bots.SlayerBot.leaves.WalkMob;

/**
 * NOTES: done
 * Checks if the bank is open when no special gear is required.
 */
public class IsBankOpenNoSpecialReq extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsBankOpenNoSpecialReq(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private CloseBank closebank = new CloseBank(Bot);
    private WalkMob walkmob = new WalkMob(Bot);

    @Override
    public boolean validate() {
        if(Bank.isOpen()){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public TreeTask failureTask() {
        return walkmob;
    }

    @Override
    public TreeTask successTask() {
        return closebank;
    }
}
