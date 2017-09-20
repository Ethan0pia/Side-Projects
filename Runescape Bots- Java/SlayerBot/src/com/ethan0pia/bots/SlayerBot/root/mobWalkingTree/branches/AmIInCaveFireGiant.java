package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.WalkCaveEntranceFireGiants;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class AmIInCaveFireGiant extends BranchTask {

    private IsPaidTrue ispaidtrue;
    private WalkCaveEntranceFireGiants walkdungeonentrance;
    private Area caveArea = new Area.Circular(new Coordinate(2693,9565,0),200);
    private OpiaSlayer bot;

    public AmIInCaveFireGiant(OpiaSlayer bot){
        this.bot=bot;
        ispaidtrue = new IsPaidTrue(bot);
        walkdungeonentrance = new WalkCaveEntranceFireGiants(bot);
    }


    @Override
    public boolean validate() {
        return caveArea.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkdungeonentrance;
    }

    @Override
    public TreeTask successTask() {
        return ispaidtrue;
    }
}
