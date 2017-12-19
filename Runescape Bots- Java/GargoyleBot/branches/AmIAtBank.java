package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIAtBank extends BranchTask {

    private IsBankOpen isbankopen;
    private AmIAtGargoyleRoom amiatgargoyleroom;
    private Area bankArea = new Area.Rectangular(new Coordinate(3512,3483,0),new Coordinate(3508,3478,0));

    private GargSlayer bot;

    public AmIAtBank(GargSlayer bot){
        this.bot=bot;
        isbankopen = new IsBankOpen(bot);
        amiatgargoyleroom = new AmIAtGargoyleRoom(bot);
    }

    @Override
    public boolean validate() {
        return bankArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amiatgargoyleroom;
    }

    @Override
    public TreeTask successTask() {
        return isbankopen;
    }
}
