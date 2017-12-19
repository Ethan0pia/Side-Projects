package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class ExitGargoyleRoom extends LeafTask {

    private GargSlayer bot;

    public ExitGargoyleRoom(GargSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        GameObject stairs = GameObjects.newQuery().actions("Climb down").names("Staircase").within(new Area.Circular(new Coordinate(3448,3539,2),4)).results().nearest();
        Camera.concurrentlyTurnTo(stairs);
        Execution.delay(1000,2000);
        if(!stairs.interact("Climb down")){
            BresenhamPath path = BresenhamPath.buildTo(stairs);
            if(path!=null){
                path.step();
            }
        }else{
            Execution.delay(3000,4000);
        }
    }
}
