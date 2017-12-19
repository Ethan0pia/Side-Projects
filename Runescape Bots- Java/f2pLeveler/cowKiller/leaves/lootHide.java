package com.ethan0pia.bots.f2pLeveler.cowKiller.leaves;

import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.rs3.local.hud.interfaces.LootInventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.region.Players;

public class lootHide extends LeafTask {

    private GroundItem hide;
    private final Area cows= new Area.Rectangular(new Coordinate(2877,3497,0), new Coordinate(2892,3479,0));

	@Override
	public void execute() {

        Player player = Players.getLocal();
	    if(player != null) {
            if (!player.isMoving()) {
                if(Skill.DEFENCE.getBaseLevel()<=6) {
                    hide = GroundItems.newQuery().names("Cowhide", "Bones", "Raw beef", "Gold charm").within(cows).results().nearest();
                }else{
                    hide = GroundItems.newQuery().names("Gold charm").within(cows).results().nearest();
                }
                if (hide != null) {
                    if (!hide.isVisible()) {
                        Camera.turnTo(hide);
                        Execution.delay(2000, 3000);
                    } else {
                        if (hide.interact("Take")) {
                            Execution.delayUntil(() -> !hide.isValid(), 3000);
                        } else {
                            Camera.concurrentlyTurnTo(hide);
                        }
                    }
                }
            }
        }
	}
}