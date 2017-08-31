package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks if at a bank when special item is needed.
 */
public class AtBankToWithdrawSpecial extends BranchTask {

    private GoodAssSlayerBot Bot;

    public AtBankToWithdrawSpecial(GoodAssSlayerBot bot){
        Bot=bot;
        isbankopenspecialneeded = new IsBankOpenSpecialNeeded(Bot);
        incombatneedspecialitem = new InCombatNeedSpecialItem(Bot);
    }

    private IsBankOpenSpecialNeeded isbankopenspecialneeded;
    private InCombatNeedSpecialItem incombatneedspecialitem;

    private Area bankArea = new Area.Rectangular( new Coordinate(2885,3538,0), new Coordinate(2893, 3534, 0));

    @Override
    public boolean validate() {

        return Bot.player !=null && bankArea.contains(Bot.player) || new Area.Circular(new Coordinate(3449, 3717, 0), 40).contains(Bot.player);
    }

    @Override
    public TreeTask failureTask() {
        return incombatneedspecialitem;
    }

    @Override
    public TreeTask successTask() {
        return isbankopenspecialneeded;
    }
}
