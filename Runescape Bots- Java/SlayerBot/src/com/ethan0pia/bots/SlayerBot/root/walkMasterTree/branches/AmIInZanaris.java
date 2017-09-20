package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.WalkLumbHouse;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.WalkMaster;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIInZanaris extends BranchTask {

    private WalkMaster walkmaster;
    private WalkLumbHouse walklumbhouse;
    private OpiaSlayer bot;
    private Area zanaris =  new Area.Circular(new Coordinate(2437,4442,0),150);
    private Area master = new Area.Circular(new Coordinate(2448, 4430, 0),2);

    public AmIInZanaris(OpiaSlayer bot){
        this.bot=bot;
        walklumbhouse = new WalkLumbHouse(bot);
        walkmaster = new WalkMaster(bot,master);
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
        return walkmaster;
    }
}
