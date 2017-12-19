package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkMob;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.WalkLumbHouse;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class AmIInsideZanaris extends BranchTask {

    private WalkMob walkmob;
    private WalkLumbHouse walklumbhouse;
    private Area zanaris =  new Area.Circular(new Coordinate(2437,4442,0),250);
    private OpiaSlayer bot;

    public AmIInsideZanaris(OpiaSlayer bot){
        this.bot=bot;
        walkmob = new WalkMob(bot);
        walklumbhouse = new WalkLumbHouse(bot);
    }


    @Override
    public boolean validate() {
        return zanaris.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walklumbhouse;
    }

    @Override
    public TreeTask successTask() {
        return walkmob;
    }
}
