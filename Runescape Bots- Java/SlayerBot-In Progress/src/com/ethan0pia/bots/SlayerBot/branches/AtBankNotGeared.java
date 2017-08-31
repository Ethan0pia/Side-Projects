package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Am I at the bank if not geared.
 */
public class AtBankNotGeared extends BranchTask {

    private GoodAssSlayerBot Bot;

    public AtBankNotGeared(GoodAssSlayerBot bot){
        Bot=bot;
        isbankopennotgeared = new IsBankOpenNotGeared(Bot);
        incombatnotgeared = new InCombatNotGeared(Bot);
    }

    private IsBankOpenNotGeared isbankopennotgeared;
    private InCombatNotGeared incombatnotgeared;

    private Area bankArea = new Area.Rectangular( new Coordinate(2885,3538,0), new Coordinate(2893, 3534, 0));

    @Override
    public boolean validate() {

        return Bot.player !=null && bankArea.contains(Bot.player) || new Area.Circular(new Coordinate(3449, 3717, 0), 40).contains(Bot.player);
    }

    @Override
    public TreeTask failureTask() {
        return incombatnotgeared;
    }

    @Override
    public TreeTask successTask() {
        return isbankopennotgeared;
    }
}
