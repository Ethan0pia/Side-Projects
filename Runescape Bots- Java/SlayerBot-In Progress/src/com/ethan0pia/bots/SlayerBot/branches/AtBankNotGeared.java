package com.ethan0pia.bots.SlayerBot.branches;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import static sun.audio.AudioPlayer.player;

/**
 * NOTES: done
 * Am I at the bank if not geared.
 */
public class AtBankNotGeared extends BranchTask {

    private IsBankOpenNotGeared isbankopennotgeared = new IsBankOpenNotGeared();
    private InCombatNotGeared incombatnotgeared = new InCombatNotGeared();

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
        return incombatnotgeared;
    }

    @Override
    public TreeTask successTask() {
        return isbankopennotgeared;
    }
}
