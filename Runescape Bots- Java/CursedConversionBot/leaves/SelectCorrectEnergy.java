package com.ethan0pia.bots.CursedConversionBot.leaves;

import com.ethan0pia.bots.CursedConversionBot.CursedConversionBot;
import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class SelectCorrectEnergy extends LeafTask {

    private CursedConversionBot bot;

    public SelectCorrectEnergy(CursedConversionBot bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        String energy = bot.getEnergy();
        if(MakeXInterface.selectItem(energy)){
            Execution.delayUntil(()->MakeXInterface.isSelectedItem(energy),2000);
        }
    }
}
