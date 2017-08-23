package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.region.Players;

public class lootHide extends LeafTask {

    private Player player;
    private GroundItem hide;
    final Area cows= new Area.Rectangular(new Coordinate(2881,3493,0), new Coordinate(2890,3481,0));

	@Override
	public void execute() {
	    player = Players.getLocal();
	    if(player != null) {
            if (!player.isMoving()) {
                hide = GroundItems.newQuery().names("Cowhide").within(cows).results().nearest();
                if (hide != null) {
                    if (!hide.isVisible()) {
                        Camera.turnTo(hide);
                        Execution.delayUntil(() -> !hide.isVisible(), 500, 2000);
                    } else {
                        hide.interact("Take", "Cowhide");
                        Execution.delayUntil(() -> !hide.isValid(), 800, 3000);
                    }
                }
            }
        }
	}
}