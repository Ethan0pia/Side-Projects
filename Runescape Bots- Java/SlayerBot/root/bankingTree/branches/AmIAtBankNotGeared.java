package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Do we have food, special item, runes/arrows, and can combat stuff be used.
 */
public class AmIAtBankNotGeared extends BranchTask {

    private IsBankOpenNotGeared isbankopennotgeared;
    private AmIInCombatNeedBank amIInCombatNeedBank;
    private OpiaSlayer bot;

    public AmIAtBankNotGeared(OpiaSlayer bot){
        this.bot=bot;
        isbankopennotgeared = new IsBankOpenNotGeared(bot);
        amIInCombatNeedBank = new AmIInCombatNeedBank(bot);
    }

    @Override
    public boolean validate() {
        return bot.getUtils().amIatBank();
    }

    @Override
    public TreeTask failureTask() {
        return amIInCombatNeedBank;
    }

    @Override
    public TreeTask successTask() {
        return isbankopennotgeared;
    }
}
