package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class InteractGameObject extends LeafTask {

    private OpiaSlayer bot;
    private String objectName;
    private String interactionName;
    private Area doorArea;

    public InteractGameObject(OpiaSlayer bot, String objectName, String interactionName){
        this.bot=bot;
        this.objectName=objectName;
        this.interactionName=interactionName;
    }

    public InteractGameObject(OpiaSlayer bot, String objectName, String interactionName, Area doorArea){
        this.bot=bot;
        this.objectName=objectName;
        this.interactionName=interactionName;
        this.doorArea=doorArea;
    }

    @Override
    public void execute() {
        GameObject object;
        Player player = bot.getPlayer();
        if(doorArea==null) {
            object = GameObjects.newQuery().names(objectName).actions(interactionName).results().nearest();
            if (object != null && bot.getPlayer() != null) {
                if(!object.interact(interactionName)){
                    Camera.concurrentlyTurnTo(object);
                }else{
                    Execution.delayUntil(()->!player.isMoving(),10000);
                }
            }
        }else{
            object = GameObjects.newQuery().names(objectName).actions(interactionName).within(doorArea).results().nearest();
            if (object != null && bot.getPlayer() != null) {
                if(!object.interact(interactionName)){
                    Camera.concurrentlyTurnTo(object);
                }else{
                    Execution.delayUntil(()->!player.isMoving(),10000);
                }
            }
        }
        Execution.delayUntil(()-> object==null || !object.isValid(), 1000,3000);
        bot.getUtils().stuckCheck(41);
    }
}
