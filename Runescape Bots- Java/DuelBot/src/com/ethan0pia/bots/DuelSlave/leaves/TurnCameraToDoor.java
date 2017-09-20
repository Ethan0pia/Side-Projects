package com.ethan0pia.bots.DuelSlave.leaves;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class TurnCameraToDoor extends LeafTask {

    private DuelingSlave bot;

    public TurnCameraToDoor(DuelingSlave bot){
        this.bot=bot;
    }



    @Override
    public void execute() {
        GameObject door = GameObjects.newQuery().names("Door").actions("Forfeit").results().nearest();
        Area doorArea = new Area.Circular(new Coordinate(3367,3251,0), 4);
        if(!doorArea.contains(bot.getPlayer()) && !bot.isMaster()){
            BresenhamPath path = BresenhamPath.buildTo(new Area.Circular(doorArea.getCenter(),2));
            if(path!=null){
                path.step();
            }
        }
        else if(bot.getPlayer() != null && !bot.getPlayer().isMoving() && !bot.isMaster() && door!=null) {
            if (!door.isVisible()) {
                Camera.concurrentlyTurnTo(door);
                Execution.delay(500, 1000);
            } else if(ChatDialog.getOptions().isEmpty()) {
                if (!door.interact("Forfeit")) {
                    Camera.concurrentlyTurnTo(door);
                    Execution.delay(500, 1000);
                    door.click();
                }
            }else{
                ChatDialog.Option forfeitOption = ChatDialog.getOption(1);
                if(forfeitOption!=null) {
                    if(!forfeitOption.select(true)){
                        forfeitOption.select(false);
                    }
                }
            }
        }
    }
}
