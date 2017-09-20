package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAlchemyEnabledBank extends BranchTask {

    private DoWeNeedAndHaveRunes doWeNeedAndHaveRunes;
    private DoesBankContainAlchRunes doesBankContainAlchRunes;
    private OpiaSlayer bot;

    public IsAlchemyEnabledBank(OpiaSlayer bot){
        this.bot=bot;
        doWeNeedAndHaveRunes = new DoWeNeedAndHaveRunes(bot);
        doesBankContainAlchRunes = new DoesBankContainAlchRunes(bot);
    }

    @Override
    public boolean validate() {
        return bot.isAlch() && (!Inventory.contains(554) || !Inventory.contains(561)) && Skill.MAGIC.getCurrentLevel()>54;
    }

    @Override
    public TreeTask failureTask() {
        return doWeNeedAndHaveRunes;
    }

    @Override
    public TreeTask successTask() {
        return doesBankContainAlchRunes;
    }
}
