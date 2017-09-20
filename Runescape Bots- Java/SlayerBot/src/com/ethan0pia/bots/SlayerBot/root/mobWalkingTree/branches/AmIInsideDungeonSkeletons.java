package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkMob;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches.AmIByEdgevilleBank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class AmIInsideDungeonSkeletons extends BranchTask {

    private WalkMob walkmob;
    private AmIByEdgevilleBank amibyedgevillebank;
    private Area caveArea = new Area.Circular( new Coordinate(3097,9869, 0), 250);
    private OpiaSlayer bot;

    public AmIInsideDungeonSkeletons(OpiaSlayer bot){
        this.bot=bot;
        amibyedgevillebank = new AmIByEdgevilleBank(bot);
        walkmob = new WalkMob(bot);
    }


    @Override
    public boolean validate() {
        return caveArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amibyedgevillebank;
    }

    @Override
    public TreeTask successTask() {
        return walkmob;
    }
}
