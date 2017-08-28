package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.LoadPreset;
import com.ethan0pia.bots.SlayerBot.leaves.OpenBank;

/**
 * NOTES: done
 * Checks if bank is open.
 */
public class IsBankOpenNotGeared extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsBankOpenNotGeared(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private LoadPreset loadpreset = new LoadPreset(Bot);
    private OpenBank openbank = new OpenBank(Bot);

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
        return openbank;
    }

    @Override
    public TreeTask successTask() {
        return loadpreset;
    }
}
