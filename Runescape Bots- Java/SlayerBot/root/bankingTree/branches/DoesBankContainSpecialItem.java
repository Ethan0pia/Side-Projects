package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.StopBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks bank for special item.
 */
public class DoesBankContainSpecialItem extends BranchTask {

    private IsSpecialEquippable isspecialequippable;
    private StopBot stopbot;
    private OpiaSlayer bot;

    public DoesBankContainSpecialItem(OpiaSlayer bot){
        this.bot=bot;
        isspecialequippable = new IsSpecialEquippable(bot);
        stopbot = new StopBot(bot);
    }

    @Override
    public boolean validate() {
        return Bank.contains(bot.getMonster().getSpecialItem());
    }

    @Override
    public TreeTask failureTask() {
        return stopbot;
    }

    @Override
    public TreeTask successTask() {
        return isspecialequippable;
    }
}
