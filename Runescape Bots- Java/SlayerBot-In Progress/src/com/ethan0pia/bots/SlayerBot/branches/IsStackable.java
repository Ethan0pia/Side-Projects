package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if any item on the ground is stackable.
 */
public class IsStackable extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsStackable(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private IsInventoryFullStackable isinventoryfullstackable = new IsInventoryFullStackable(Bot);
    private InCombatAtMob incombatatmob = new InCombatAtMob(Bot);

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return incombatatmob;
    }

    @Override
    public TreeTask successTask() {
        return isinventoryfullstackable;
    }
}
