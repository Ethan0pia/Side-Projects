package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class CheckGear extends BranchTask {

    private IsBankOpenGeared isbankopengeared;
    private AmIAtBank amiatbank;

    private GargSlayer bot;

    public CheckGear(GargSlayer bot){
        this.bot=bot;
        isbankopengeared = new IsBankOpenGeared(bot);
        amiatbank = new AmIAtBank(bot);
    }

    @Override
    public boolean validate() {
        boolean airRunes=true;
        if(bot.isUsingMagic()) {
            airRunes = Inventory.contains(556) && Inventory.newQuery().ids(556).results().first().getQuantity() > 5;
        }
        boolean fireRunes = Inventory.contains(554) && Inventory.newQuery().ids(554).results().first().getQuantity()>5;
        boolean natureRunes = Inventory.contains(561);
        boolean inventory = true;
        if(Inventory.isFull() && !Inventory.contains(bot.getFoodType())){
            inventory=false;
        }


        return airRunes && fireRunes && natureRunes && inventory && !bot.isBankBool();
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
