package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
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
    }

    private IsBankOpenSpecialNeeded isbankopenspecialneeded = new IsBankOpenSpecialNeeded(Bot);
    private InCombatNeedSpecialItem incombatneedspecialitem = new InCombatNeedSpecialItem(Bot);

    private Coordinate bankArea = new Coordinate(2888,3535,0);
    private Player player;

    @Override
    public boolean validate() {
        player = Players.getLocal();
        if(player !=null) {
            if (player.distanceTo(bankArea) < 10) {
                return true;
            } else {
                return false;
            }
        }
        return false;
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
