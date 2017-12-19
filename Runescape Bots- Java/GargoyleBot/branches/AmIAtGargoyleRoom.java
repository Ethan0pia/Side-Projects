package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.ExitGargoyleRoom;
import com.ethan0pia.bots.GargoyleBot.leaves.WalkBank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIAtGargoyleRoom extends BranchTask {

    private ExitGargoyleRoom exitgargoyleroom;
    private WalkBank walkbank;
    private Area gargoyleArea = new Area.Rectangular(new Coordinate(3433,3569,2), new Coordinate(3448,3539,2));

    private GargSlayer bot;

    public AmIAtGargoyleRoom(GargSlayer bot){
        this.bot=bot;
        walkbank = new WalkBank(bot);
        exitgargoyleroom = new ExitGargoyleRoom(bot);
    }

    @Override
    public boolean validate() {
        return gargoyleArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkbank;
    }

    @Override
    public TreeTask successTask() {
        return exitgargoyleroom;
    }
}
