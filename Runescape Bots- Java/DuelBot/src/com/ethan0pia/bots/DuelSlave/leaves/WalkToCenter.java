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

    private DuelingSlave bot;
    private Area duelingLobby;
    private Area duelingLobbyCenter;

    public WalkToCenter(DuelingSlave bot){
        this.bot=bot;
        duelingLobby = bot.getDuelingLobby();
        duelingLobbyCenter=bot.getDuelingLobbyCenter();
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
