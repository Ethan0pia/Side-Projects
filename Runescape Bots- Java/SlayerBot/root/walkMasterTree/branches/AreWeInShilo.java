package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.WalkMaster;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeInShilo extends BranchTask {

    private WalkMaster walkmaster;
    private AreWeByCart areWeByCart;
    private OpiaSlayer bot;
    private Area shilo = new Area.Circular(new Coordinate(2852,2972,0),40);
    private Area duradelFloor = new Area.Circular(new Coordinate(2869,2982,1),15);
    private Area duradel = new Area.Circular(new Coordinate(2869,2982,1),2);

    public AreWeInShilo(OpiaSlayer bot){
        this.bot=bot;
        areWeByCart = new AreWeByCart(bot);
        walkmaster = new WalkMaster(bot,duradel);
    }


    @Override
    public boolean validate() {
        return shilo.contains(bot.getPlayer())||duradelFloor.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return areWeByCart;
    }

    @Override
    public TreeTask successTask() {
        return walkmaster;
    }
}
