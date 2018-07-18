package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsGearCorrect extends BranchTask {

    private IsBankOpenGeared isbankopengeared;
    private AmIAtBank amiatbank;

    private GargSlayer bot;

    public IsGearCorrect(GargSlayer bot){
        this.bot=bot;
        isbankopengeared = new IsBankOpenGeared(bot);
        amiatbank = new AmIAtBank(bot);
    }

    @Override
    public boolean validate() {

        boolean fireRunes = Inventory.contains(554) && Inventory.newQuery().ids(554).results().first().getQuantity()>5;
        boolean natureRunes = Inventory.contains(561);
        boolean inventory = true;
        if(Inventory.isFull() && Inventory.newQuery().actions("Eat").results().isEmpty()){
            inventory=false;
        }


        return fireRunes && natureRunes && inventory && !bot.isBankBool();
    }

    @Override
    public TreeTask failureTask() {
        return amiatbank;
    }

    @Override
    public TreeTask successTask() {
        return isbankopengeared;
    }
}
