package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.status.CombatGauge;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.hybrid.region.Players;

public class attackCow extends LeafTask {
	private Npc cow;
    final Area cows= new Area.Rectangular(new Coordinate(2881,3493,0), new Coordinate(2890,3481,0));
	@Override
	public void execute() {
        cow = Npcs.getLoaded("Cow").sortByDistance().get(1);
        if(cow !=null|| cows.contains(cow.getPosition())) {
            if (!cow.isVisible()) {
                Camera.turnTo(cow);
            }
            if (!Players.getLocal().isMoving()) {
                cow.interact("Attack", "Cow");
                Execution.delayUntil(()-> {
                    CombatGauge gauge = cow.getHealthGauge();
                    return gauge == null || gauge.getPercent() <= 0 || !cow.isValid();
                }, 2000, 8000);
            }
        }
	}

}