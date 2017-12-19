package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves;

import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class AttackWizard extends LeafTask {

    private Area mobs = new Area.Circular(new Coordinate(2909,3334,0),8);

    @Override
    public void execute() {
        Npc wizard = Npcs.newQuery().names("Dark wizard").within(mobs).filter(i->(i.getSpotAnimationIds().isEmpty() || !(4075 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4225))).results().sortByDistance().limit(0, 2).random();
        Player player = Players.getLocal();
        if(wizard !=null && player!=null) {
            if (!wizard.isVisible()) {
                Camera.turnTo(wizard);
            }
            if (!player.isMoving()) {
                if(wizard.interact("Attack")) {
                    Execution.delay(1000, 1500);
                }
            }
        }
    }
}
