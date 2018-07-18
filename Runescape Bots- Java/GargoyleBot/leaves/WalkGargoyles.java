package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkGargoyles extends LeafTask {

    private GargSlayer bot;
    private Area gargs = new Area.Circular(new Coordinate(3442,3549,2),40);
    private Area groundFloor = new Area.Circular(new Coordinate(3445,3542,0), 5);
    private Area firstFloor = new Area.Circular(new Coordinate(3445,3542,1), 5);

    public WalkGargoyles(GargSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(!gargs.contains(bot.getPlayer()) && !firstFloor.contains(bot.getPlayer()) && !groundFloor.contains(bot.getPlayer())){
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(groundFloor.getCenter());
            if(path!=null){
                path.step();
            }
        }else{
            GameObject stairs = GameObjects.newQuery().names("Staircase").actions("Climb up").results().nearest();
            if(stairs!=null){
                stairs.interact("Climb up");
            }
        }
    }
}
