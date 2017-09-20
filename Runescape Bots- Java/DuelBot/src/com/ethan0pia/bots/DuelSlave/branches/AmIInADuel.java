package com.ethan0pia.bots.DuelSlave.branches;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.ethan0pia.bots.DuelSlave.leaves.TurnCameraToDoor;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AmIInADuel extends BranchTask {

    private Area duelArea;
    private TurnCameraToDoor turnCameraToDoor;
    private AmIAtDuelArenaLobbyArea amiatduelarenalobbyarea;
    private DuelingSlave bot;

    public AmIInADuel(DuelingSlave bot){
        this.bot=bot;
        turnCameraToDoor = new TurnCameraToDoor(bot);
        amiatduelarenalobbyarea = new AmIAtDuelArenaLobbyArea(bot);
        duelArea=bot.getDuelArea();
    }

    @Override
    public boolean validate() {

        Player player = bot.getPlayer();
        if (player != null) {
            if (duelArea.contains(player)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return amiatduelarenalobbyarea;
    }

    @Override
    public TreeTask successTask() {
        return turnCameraToDoor;
    }
}
