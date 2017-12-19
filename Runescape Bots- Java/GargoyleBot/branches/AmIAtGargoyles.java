package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.WalkGargoyles;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIAtGargoyles extends BranchTask {

    private AreItemsWorthLooting areitemsworthlooting;
    private WalkGargoyles walkgargoyles;
    private Area gargoyleArea = new Area.Rectangular(new Coordinate(3433,3569,2), new Coordinate(3448,3539,2));

    private GargSlayer bot;

    public AmIAtGargoyles(GargSlayer bot){
        this.bot=bot;
        areitemsworthlooting = new AreItemsWorthLooting(bot);
        walkgargoyles = new WalkGargoyles(bot);
    }

    @Override
    public boolean validate() {
        return gargoyleArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkgargoyles;
    }

    @Override
    public TreeTask successTask() {
        return areitemsworthlooting;
    }
}
