package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * check if inventory is full w/o food, special item, runes/arrows, and can combat stuff be used.
 */
public class AreWeGeared extends BranchTask {

    private AmIAtBankGeared amIAtBankGeared;
    private AmIAtBankNotGeared amiatbanknotgeared;
    private OpiaSpiritualMages bot;

    public AreWeGeared(OpiaSpiritualMages bot){
        this.bot=bot;
        amIAtBankGeared = new AmIAtBankGeared(bot);
        amiatbanknotgeared = new AmIAtBankNotGeared(bot);
    }

    @Override
    public boolean validate() {
        if(bot.getUtils().amIatBank()){
            if(!Inventory.contains("Beltfish")){
                return false;
            }else if(bot.isAlch() && !Inventory.contains(554) || !Inventory.contains(561)){
                return false;
            }
        }


        if (Equipment.getItemIn(Equipment.Slot.AMMUNITION.getIndex()) == null) {
                return false;
        }

        boolean abilityCheck = ActionBar.newQuery().results().first()!=null && ActionBar.newQuery().results().first().isActivatable();

        if (Inventory.isFull() && !Inventory.contains("Beltfish")) {
            bot.setGetFood(true);
            return false;
        }

        boolean quickTP = Inventory.contains(8010);

        if(!ActionBar.isAutoRetaliating()){
            ActionBar.toggleAutoRetaliation();
        }

        return quickTP && abilityCheck && !bot.isGetFood() && bot.isBankBool();
    }

    @Override
    public TreeTask failureTask() {
        return amiatbanknotgeared;
    }

    @Override
    public TreeTask successTask() {
        return amIAtBankGeared;
    }

}
