package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkOverWall extends LeafTask {

    @Override
    public void execute() {
        GameObject wall = GameObjects.newQuery().names("Crumbling wall").actions("Climb-over").results().nearest();
        if(wall!=null){
            if(wall.interact("Climb-over")){
                Execution.delay(1000,3000);
            }
        }
    }
}
