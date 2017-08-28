package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * checks if player has armor, weapon, and food.
 */
public class IsGeared extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsGeared(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private RequiresSpecialItem requiresspecialitem = new RequiresSpecialItem(Bot);
    private AtBankNotGeared atbanknotgeared = new AtBankNotGeared(Bot);

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return atbanknotgeared;
    }

    @Override
    public TreeTask successTask() {
        return requiresspecialitem;
    }
}
