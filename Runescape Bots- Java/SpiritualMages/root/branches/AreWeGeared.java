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
    private DoesBankNeedToBeClosed amiatbanknotgeared;
    private OpiaSpiritualMages bot;

    public AreWeGeared(OpiaSpiritualMages bot){
        this.bot=bot;
        amIAtBankGeared = new AmIAtBankGeared(bot);
        amiatbanknotgeared = new DoesBankNeedToBeClosed(bot);
    }

    @Override
    public boolean validate() {

        if (Equipment.getItemIn(Equipment.Slot.AMMUNITION.getIndex()) == null) {
            return false;
        }

        if (Inventory.isFull() && !Inventory.contains("Beltfish")) {
            bot.setGetFood(true);
            return false;
        }

        boolean quickTP = Inventory.contains(8010);

        if (!ActionBar.isAutoRetaliating()) {
            ActionBar.toggleAutoRetaliation();
        }

        return quickTP && !bot.isGetFood() && bot.isBankBool();

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
