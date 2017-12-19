package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.WalkMaster;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsMasterDuradel extends BranchTask {

    private AreWeInShilo areWeInShilo;
    private WalkMaster walkMaster;
    private Area turael =  new Area.Circular(new Coordinate(2911,3422,0), 4);
    private OpiaSlayer bot;

    public IsMasterDuradel(OpiaSlayer bot){
        this.bot=bot;
        areWeInShilo = new AreWeInShilo(bot);
        walkMaster = new WalkMaster(bot,turael);
    }


    @Override
    public boolean validate() {
        int master = bot.getMaster();
        return master==5 || master==6;
    }

    @Override
    public TreeTask failureTask() {
        return walkMaster;
    }

    @Override
    public TreeTask successTask() {
        return areWeInShilo;
    }
}
