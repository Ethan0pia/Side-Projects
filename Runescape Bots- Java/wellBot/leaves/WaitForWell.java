package com.ethan0pia.bots.wellBot.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WaitForWell extends LeafTask {

    @Override
    public void execute() {
        if(MakeXInterface.isOpen()){
            MakeXInterface.close();
        }
        Execution.delayUntil(()->!GameObjects.newQuery().within(new Area.Circular(Players.getLocal().getPosition(),5)).names("Portable well").actions("Mix Potions").results().isEmpty(),270000);
        //Execution.delayUntil(()->!GameObjects.newQuery().within(new Area.Circular(Players.getLocal().getPosition(),5)).names("Portable forge").actions("Smelt").results().isEmpty(),270000);
        Execution.delay(500,6000);
        if(GameObjects.newQuery().within(new Area.Circular(Players.getLocal().getPosition(),5)).names("Portable well").actions("Mix Potions").results().isEmpty()){
        //if(GameObjects.newQuery().within(new Area.Circular(Players.getLocal().getPosition(),5)).names("Portable forge").actions("Smith").results().isEmpty()){
            Environment.getBot().stop("end");
        }
    }
}
