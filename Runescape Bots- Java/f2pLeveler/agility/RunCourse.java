package com.ethan0pia.bots.f2pLeveler.agility;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class RunCourse extends LeafTask {

    private Area firstObst = new Area.Rectangular(new Coordinate(2915,3552,0),new Coordinate(2920,3551,0));
    private Area secondObst = new Area.Rectangular(new Coordinate(2921,3561,0), new Coordinate(2918,3558,0));
    private Area thirdObst = new Area.Rectangular(new Coordinate(2916,3564,1), new Coordinate(2920,3564,1));
    private Area thirdObst2 = new Area.Rectangular(new Coordinate(2920,3564,1),new Coordinate(2918,3562,1));
    private Area fourthObst = new Area.Rectangular(new Coordinate(2912,3564,1), new Coordinate(2910,3564,1));
    private Area fourthObst2 = new Area.Rectangular(new Coordinate(2910,3561,1), new Coordinate(2910,3564,1));
    private Area fifthObst = new Area.Rectangular(new Coordinate(2912,3561,1), new Coordinate(2912,3563,1));
    private Area sixthObst = new Area.Rectangular(new Coordinate(2916,3561,1), new Coordinate(2917,3563,1));
    private Area seventhObst = new Area.Rectangular(new Coordinate(2915,3554,1),new Coordinate(2917,3553,1));

    @Override
    public void execute() {
        Player player = Players.getLocal();
        GameObject obstacle;
        if(secondObst.contains(player)){
            obstacle= GameObjects.newQuery().names("Wall").actions("Climb-up").results().nearest();
            if(obstacle!=null){
                if(obstacle.interact("Climb-up")){
                    Execution.delayUntil(()->thirdObst2.contains(player) || thirdObst.contains(player),6000);
                }
            }
        }else if(thirdObst.contains(player) || thirdObst2.contains(player)){
            obstacle= GameObjects.newQuery().names("Balancing ledge").actions("Walk-across").results().nearest();
            if(obstacle!=null){
                if(obstacle.interact("Walk-across")){
                    Execution.delayUntil(()->fourthObst.contains(player)||fourthObst2.contains(player),6000);
                }
            }
        }else if(fourthObst.contains(player)|| fourthObst2.contains(player)){
            obstacle= GameObjects.newQuery().names("Obstacle low wall").actions("Climb-over").results().nearest();
            if(obstacle!=null){
                if(obstacle.interact("Climb-over")){
                    Execution.delayUntil(()->fifthObst.contains(player),6000);
                }
            }
        }else if(fifthObst.contains(player)){
            obstacle= GameObjects.newQuery().names("Rope swing").actions("Swing-on").results().nearest();
            if(obstacle!=null){
                if(obstacle.interact("Swing-on")){
                    Execution.delayUntil(()->sixthObst.contains(player),6000);
                }
            }
        }else if(sixthObst.contains(player)){
            obstacle= GameObjects.newQuery().names("Monkey bars").actions("Swing-across").results().nearest();
            if(obstacle!=null){
                if(obstacle.interact("Swing-across")){
                    Execution.delayUntil(()->seventhObst.contains(player),6000);
                }
            }
        }else if(seventhObst.contains(player)){
            obstacle= GameObjects.newQuery().names("Ledge").actions("Jump-down").results().nearest();
            if(obstacle!=null){
                if(obstacle.interact("Jump-down")){
                    Execution.delayUntil(()->firstObst.contains(player),6000);
                }
            }
        }else{
            obstacle= GameObjects.newQuery().names("Log beam").actions("Walk").results().nearest();
            if(obstacle!=null){
                if(obstacle.interact("Walk")){
                    Execution.delayUntil(()->secondObst.contains(player),6000);
                }
            }
        }

    }
}
