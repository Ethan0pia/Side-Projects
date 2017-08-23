package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.hybrid.entities.Player;
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

    private GetTask gettask = new GetTask();
    private InCombatMaster incombatmaster = new InCombatMaster();

    private Coordinate slayerMasterArea= new Coordinate(2911,3422,0);;
    private Player player;

    @Override
    public boolean validate() {
        player = Players.getLocal();
        if(player !=null) {
            if(slayerMasterArea!=null) {
                if (player.distanceTo(slayerMasterArea) < 6) {
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
