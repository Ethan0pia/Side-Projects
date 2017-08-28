package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.GetTask;

/**
 * NOTES:
 * checks if player is at slayer master.
 */
public class AtMaster extends BranchTask {

    private GoodAssSlayerBot Bot;

    public AtMaster(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private GetTask gettask = new GetTask(Bot);
    private InCombatMaster incombatmaster = new InCombatMaster(Bot);

    @Override
    public boolean validate() {
        Area slayerMasterArea= Bot.mobList.masters(Bot.master);
        Player player = Players.getLocal();
        if(player !=null) {
            if(slayerMasterArea!=null) {
                if (slayerMasterArea.contains(player)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;

    }

    @Override
    public TreeTask failureTask() {
        return incombatmaster;
    }

    @Override
    public TreeTask successTask() {
        return gettask;
    }
}
