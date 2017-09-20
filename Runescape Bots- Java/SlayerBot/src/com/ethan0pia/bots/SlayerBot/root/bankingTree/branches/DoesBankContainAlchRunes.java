package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.WithdrawAlchRunes;
import com.ethan0pia.bots.SlayerBot.root.leaves.TurnOffAlch;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class DoesBankContainAlchRunes extends BranchTask {

    private WithdrawAlchRunes withdrawAlchRunes;
    private TurnOffAlch turnOffAlch;

    public DoesBankContainAlchRunes(OpiaSlayer bot){
        withdrawAlchRunes = new WithdrawAlchRunes(bot);
        turnOffAlch = new TurnOffAlch(bot);
    }

    @Override
    public boolean validate() {
        Bank.contains("Nature rune");
        return Skill.MAGIC.getCurrentLevel()>54 && Bank.contains(554) && Bank.contains(561);
    }

    @Override
    public TreeTask failureTask() {
        return turnOffAlch;
    }

    @Override
    public TreeTask successTask() {
        return withdrawAlchRunes;
    }
}

