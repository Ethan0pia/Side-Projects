package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class AmIByDungeonEntranceFireGiants extends BranchTask {

    private DidIPayMan didipayman;
    private AmIInCaveFireGiant amiincavefiregiant;
    private Area entrance = new Area.Circular(new Coordinate(2745,3152,0),5);
    private OpiaSlayer bot;

    public AmIByDungeonEntranceFireGiants(OpiaSlayer bot){
        this.bot=bot;
        didipayman = new DidIPayMan(bot);
        amiincavefiregiant = new AmIInCaveFireGiant(bot);
    }


    @Override
    public boolean validate() {
        return entrance.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amiincavefiregiant;
    }

    @Override
    public TreeTask successTask() {
        return didipayman;
    }
}
