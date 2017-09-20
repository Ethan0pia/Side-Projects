package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.WalkMaster;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsMasterVannaka extends BranchTask {

    private AmIByTrapdoor amibytrapdoor;
    private WalkMaster walkmaster;
    private OpiaSlayer bot;

    public IsMasterVannaka(OpiaSlayer bot){
        this.bot=bot;
        amibytrapdoor = new AmIByTrapdoor(bot);
        Area master =  new Area.Circular(new Coordinate(2911,3422,0), 4);
        walkmaster = new WalkMaster(bot,master);
    }


    @Override
    public boolean validate() {
        return bot.getMaster().equalsIgnoreCase("Vannaka");
    }

    @Override
    public TreeTask failureTask() {
        return walkmaster;
    }

    @Override
    public TreeTask successTask() {
        return amibytrapdoor;
    }
}
