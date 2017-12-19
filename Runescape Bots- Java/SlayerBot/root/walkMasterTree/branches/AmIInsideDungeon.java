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
public class AmIInsideDungeon extends BranchTask {

    private WalkMaster walkmaster;
    private AmIByEdgevilleBank amibyedgevillebank;
    private OpiaSlayer bot;
    private Area vannaka = new Area.Rectangular(new Coordinate(3144,9915,0), new Coordinate(3149,9913,0));

    public AmIInsideDungeon(OpiaSlayer bot){
        this.bot=bot;
        amibyedgevillebank = new AmIByEdgevilleBank(bot);
        walkmaster = new WalkMaster(bot,vannaka);
    }


    @Override
    public boolean validate() {
        Area caveArea = new Area.Circular(new Coordinate(3097, 9869, 0), 225);
        return caveArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amibyedgevillebank;
    }

    @Override
    public TreeTask successTask() {
        return walkmaster;
    }
}
