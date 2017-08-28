package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Are there items on the ground?
 */
public class CheckGround extends BranchTask {

    private GoodAssSlayerBot Bot;

    public CheckGround(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private ItemWorthOverX itemworthoverx = new ItemWorthOverX(Bot);
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
        return itemworthoverx;
    }
}
