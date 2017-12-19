package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class InteractGameObject extends LeafTask {

    private OpiaSpiritualMages bot;
    private String objectName;
    private String interactionName;
    public InteractGameObject(OpiaSpiritualMages bot, String objectName, String interactionName){
        this.bot=bot;
        this.objectName=objectName;
        this.interactionName=interactionName;
    }

    @Override
    public void execute() {
        GameObject object;
        Player player = bot.getPlayer();
        object = GameObjects.newQuery().names(objectName).actions(interactionName).results().nearest();
        if (object != null && bot.getPlayer() != null) {
            if(!object.interact(interactionName)){
                Camera.concurrentlyTurnTo(object);
            }else{
                Execution.delayUntil(()->!player.isMoving(),10000);
            }
        }
        Execution.delayUntil(()-> object==null || !object.isValid(), 1000,3000);
        bot.getUtils().stuckCheck(8);
    }
}
