package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.CloseBank;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches.DoesMobRequireBasicSpecial;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Is the bank open?
 */
public class IsBankOpenGeared extends BranchTask {

    private CloseBank closebank;
    private DoesMobRequireBasicSpecial walkmob;
    private OpiaSlayer bot;

    public IsBankOpenGeared(OpiaSlayer bot){
        this.bot=bot;
        closebank = new CloseBank(bot);
        walkmob = new DoesMobRequireBasicSpecial(bot);
    }

    @Override
    public boolean validate() {
        return Bank.isOpen() && !bot.getMonster().getMobName().equalsIgnoreCase("Mutated Zygomite") && !bot.getMonster().getMobName().equalsIgnoreCase("Otherworldly being");
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
