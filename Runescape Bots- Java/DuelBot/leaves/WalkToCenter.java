package com.ethan0pia.bots.DuelSlave.leaves;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkToCenter extends LeafTask {

    private Area duelingLobby = new Area.Rectangular(new Coordinate(3331,3241,0), new Coordinate(3351,3223,0));
    private Area duelingLobbyCenter = new Area.Circular(duelingLobby.getCenter(),8);

    private DuelingSlave bot;

    public WalkToCenter(DuelingSlave bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        RegionPath path = RegionPath.buildTo(duelingLobbyCenter.getRandomCoordinate());
        if(path!=null){
            path.step();
        }else{
            BresenhamPath backupPath = BresenhamPath.buildTo(duelingLobbyCenter.getRandomCoordinate());
            if(backupPath!=null){
                backupPath.step();
            }
        }
    }
}
