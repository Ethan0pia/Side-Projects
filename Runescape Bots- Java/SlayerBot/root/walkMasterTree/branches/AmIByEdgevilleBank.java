package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.WalkSouthOfEdgevilleBank;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.WalkTrapdoor;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIByEdgevilleBank extends BranchTask {

    private WalkTrapdoor walktotrapdoor;
    private WalkSouthOfEdgevilleBank walksouthofedgevillebank;
    private OpiaSlayer bot;
    private Area southBank = new Area.Rectangular(new Coordinate(3089,3487,0),new Coordinate(3098,3466,0));

    public AmIByEdgevilleBank(OpiaSlayer bot){
        this.bot=bot;
        walktotrapdoor = new WalkTrapdoor(bot);
        walksouthofedgevillebank = new WalkSouthOfEdgevilleBank(bot);
    }


    @Override
    public boolean validate() {
        return southBank.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walksouthofedgevillebank;
    }

    @Override
    public TreeTask successTask() {
        return walktotrapdoor;
    }
}
