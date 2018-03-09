package com.ethan0pia.bots.CursedConversionBot.branches;

import com.ethan0pia.bots.CursedConversionBot.CursedConversionBot;
import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.CursedConversionBot.leaves.ConfirmAndWait;
import com.ethan0pia.bots.CursedConversionBot.leaves.SelectCorrectEnergy;

/**
 * NOTES:
 * 
 */
public class IsCorrectEnergySelected extends BranchTask {

    private ConfirmAndWait confirmandwait = new ConfirmAndWait();
    private SelectCorrectEnergy selectcorrectenergy;
    private CursedConversionBot bot;

    public IsCorrectEnergySelected(CursedConversionBot bot){
        selectcorrectenergy = new SelectCorrectEnergy(bot);
        this.bot=bot;
    }

    @Override
    public boolean validate() {
        return MakeXInterface.isSelectedItem(bot.getEnergy());
    }

    @Override
    public TreeTask failureTask() {
        return selectcorrectenergy;
    }

    @Override
    public TreeTask successTask() {
        return confirmandwait;
    }
}
