package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class UseGlory extends LeafTask {

    private OsrsOrbMaker bot;
    private Area edgeville = new Area.Circular(new Coordinate(3093,3491,0),30);

    public UseGlory(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("UseGlory");
        SpriteItem glory = Equipment.getItemIn(Equipment.Slot.NECK);
        if (glory != null) {
            if(glory.interact("Edgeville")){
                Execution.delayUntil(()->edgeville.contains(bot.getPlayer()),5000);
            }
        }
    }
}
