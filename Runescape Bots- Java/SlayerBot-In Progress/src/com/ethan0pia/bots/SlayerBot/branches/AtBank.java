package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Main AtBank - Checks if at bank before going to mob.
 */
public class AtBank extends BranchTask {

    private GoodAssSlayerBot Bot;

    public AtBank(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private IsBankOpenNoSpecialReq isbankopennospecialreq = new IsBankOpenNoSpecialReq(Bot);
    private AtMonster atmonster = new AtMonster(Bot);

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
        return atmonster;
    }

    @Override
    public TreeTask successTask() {
        return isbankopennospecialreq;
    }
}
