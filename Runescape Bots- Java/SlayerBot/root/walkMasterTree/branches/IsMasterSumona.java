package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.WalkMaster;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsMasterSumona extends BranchTask {

    private WalkMaster walkmasterSumona;
    private IsMasterDuradel isMasterDuradel;
    private OpiaSlayer bot;
    private Area sumona = new Area.Rectangular(new Coordinate(3357,2993,0),new Coordinate(3359,2992,0));

    public IsMasterSumona(OpiaSlayer bot){
        this.bot=bot;
        walkmasterSumona = new WalkMaster(bot,sumona);
        isMasterDuradel = new IsMasterDuradel(bot);
    }


    @Override
    public boolean validate() {
        return bot.getMaster()==4;
    }

    @Override
    public TreeTask failureTask() {
        return isMasterDuradel;
    }

    @Override
    public TreeTask successTask() {
        return walkmasterSumona;
    }
}
