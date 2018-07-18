package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
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
    private Area boulderAndEntrance=new Area.Rectangular(new Coordinate(2902,3712,0),new Coordinate(2930,3748,0));

    public InteractGameObject(OpiaSpiritualMages bot, String objectName, String interactionName){
        this.bot=bot;
        this.objectName=objectName;
        this.interactionName=interactionName;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("InteractGameObject" + objectName);
        bot.setCurrentTask("Interacting With" + objectName);
        try {
            GameObject object;
            object = GameObjects.newQuery().names(objectName).actions(interactionName).results().nearest();
            if (object != null && bot.getPlayer() != null) {
                if (!object.interact(interactionName)) {
                    Camera.concurrentlyTurnTo(object);
                } else {
                    if (object.getDefinition().getName().equals("Boulder")) {
                        Execution.delayUntil(() -> boulderAndEntrance.contains(bot.getPlayer()), 1000, 20000);
                    } else {
                        Execution.delayUntil(() -> !object.isVisible() || !object.isValid(), 1000, 5000);
                    }
                }
            }
            bot.getUtils().stuckCheck(8);
        }catch(Exception e) {
            Environment.getLogger().debug("Failed to interact with game object");
        }
    }
}
