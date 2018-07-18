package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * check if inventory is full w/o food, special item, runes/arrows, and can combat stuff be used.
 */
public class AreWeGeared extends BranchTask {

    private AmIAtMob amiatmob;
    private AreWeAtBank areWeAtBank;
    private OpiaSpiritualMages bot;

    public AreWeGeared(OpiaSpiritualMages bot){
        this.bot=bot;
        amiatmob = new AmIAtMob(bot);
        areWeAtBank = new AreWeAtBank(bot);
    }

    @Override
    public boolean validate() {
        if (Equipment.getItemIn(Equipment.Slot.AMMUNITION.getIndex()) == null && bot.isUsingAmmo()) {
            bot.setNeedBank(true);
        }

        if ((Inventory.isFull() && Inventory.newQuery().actions("Eat").results().isEmpty()) || !Inventory.contains(bot.getTeleport()) || !Inventory.containsAllOf(561,554)) {
            bot.setNeedBank(true);
        }

        if (!ActionBar.isAutoRetaliating()) {
            ActionBar.toggleAutoRetaliation();
        }

        return !bot.isNeedBank();

    }

    @Override
    public TreeTask failureTask() {
        return areWeAtBank;
    }

    @Override
    public TreeTask successTask() {
        return amiatmob;
    }

}
