package com.ethan0pia.bots.DuelSlave.branches;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.ethan0pia.bots.DuelSlave.leaves.ForfietDuel;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeInsideArena extends BranchTask {

    private Area duelArea = new Area.Polygonal(new Coordinate(3362,3259,0 ), new Coordinate(3362,3247,0 ), new Coordinate(3366,3243,0 ),new Coordinate(3390,3243,0),new Coordinate(3390,3259,0),new Coordinate(3362,3259,0 ));
    private ForfietDuel forfietDuel;
    private AreWeAtDuelArenaLobby amiatduelarenalobbyarea;
    private DuelingSlave bot;

    public AreWeInsideArena(DuelingSlave bot){
        this.bot=bot;
        forfietDuel = new ForfietDuel(bot);
        amiatduelarenalobbyarea = new AreWeAtDuelArenaLobby(bot);
    }

    @Override
    public boolean validate() {
        Player player = bot.getPlayer();
        return duelArea.contains(player);
    }

    @Override
    public TreeTask failureTask() {
        return amiatduelarenalobbyarea;
    }

    @Override
    public TreeTask successTask() {
        return forfietDuel;
    }
}
