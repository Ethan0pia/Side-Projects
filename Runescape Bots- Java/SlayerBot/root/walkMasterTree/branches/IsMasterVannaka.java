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
    private IsMasterSumona isMasterSumona;
    private OpiaSlayer bot;

    public IsMasterVannaka(OpiaSlayer bot){
        this.bot=bot;
        amibytrapdoor = new AmIByTrapdoor(bot);
        isMasterSumona = new IsMasterSumona(bot);
    }


    @Override
    public boolean validate() {
        return bot.getMaster()==2;
    }

    @Override
    public TreeTask failureTask() {
        return isMasterSumona;
    }

    @Override
    public TreeTask successTask() {
        return amibytrapdoor;
    }
}
