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
        //mobs that need to access bank to walk to mob.
        int mob = bot.getMonster().getIndex();
        return Bank.isOpen() && mob!=74 && mob!=55 && mob!=25 && mob!=89 && mob!=31 && mob!=30 && mob!=27;
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
