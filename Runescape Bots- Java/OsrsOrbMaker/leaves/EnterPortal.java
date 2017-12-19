package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EnterPortal extends LeafTask {

    private OsrsOrbMaker bot;
    private Area portalArea = new Area.Circular(new Coordinate(3326,4754,0),20);

    public EnterPortal(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("EnterPortal");
        GameObject portal = GameObjects.newQuery().names("Free-for-all portal").actions("Enter").results().nearest();
        if(portal!=null){
            if(portal.interact("Enter")){
                Execution.delayUntil(()->portalArea.contains(bot.getPlayer()),3000);
            }
        }
    }
}

