package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.ethan0pia.bots.SlayerBot.leaves.OpenBank;

/**
 * NOTES: done
 * Checks if bank is open when at bank and needs special item.
 */
public class IsBankOpenSpecialNeeded extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsBankOpenSpecialNeeded(GoodAssSlayerBot bot){
        Bot=bot;
        openbank = new OpenBank(Bot);
        doesbankcontainspecialitem = new DoesBankContainSpecialItem(Bot);
    }

    private DoesBankContainSpecialItem doesbankcontainspecialitem;
    private OpenBank openbank;

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return openbank;
    }

    @Override
    public TreeTask successTask() {
        return doesbankcontainspecialitem;
    }
}
