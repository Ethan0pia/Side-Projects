package com.ethan0pia.bots.CursedConversionBot.branches;

import com.ethan0pia.bots.CursedConversionBot.CursedConversionBot;
import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsMakeWindowOpen extends BranchTask {

    private IsCorrectEnergySelected iscorrectenergyselected;
    private DoesInvHaveCursedEnrgy doesinvhavecursedenrgy = new DoesInvHaveCursedEnrgy();

    public IsMakeWindowOpen(CursedConversionBot bot){
        iscorrectenergyselected = new IsCorrectEnergySelected(bot);
    }

    @Override
    public boolean validate() {
        return MakeXInterface.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return doesinvhavecursedenrgy;
    }

    @Override
    public TreeTask successTask() {
        return iscorrectenergyselected;
    }
}
