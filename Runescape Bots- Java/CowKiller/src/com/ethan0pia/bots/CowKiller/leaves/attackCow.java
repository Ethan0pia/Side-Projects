package com.ethan0pia.bots.CowKiller.leaves;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
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
	private Player player;
    private final Area cowArea= new Area.Rectangular(new Coordinate(2882,3491,0), new Coordinate(2889,3483,0));


    @Override
	public void execute() {
        cow = Npcs.getLoadedWithin(cowArea,"Cow").sortByDistance().limit(0, 3).random();
        player=Players.getLocal();
        if(cow !=null && player!=null) {
            if (!cow.isVisible()) {
                Camera.turnTo(cow);
            }
            if (!player.isMoving() && cow!=null) {
                cow.interact("Attack");
                Execution.delay(1000,1500);
                Execution.delayUntil(() -> {
                    CombatGauge gauge = cow.getHealthGauge();
                    return gauge == null || gauge.getPercent() <= 0 || !cow.isValid();
                }, 8000);
            }
        }
	}

}