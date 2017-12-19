package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.leaves.EmptyLeaf;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class IsTaskInGWD extends BranchTask {

    private AmIInZammyRoom ispathtomobnull;
    private EmptyLeaf emptyleaf;
    OpiaSlayer bot;

    public IsTaskInGWD(OpiaSlayer bot){
        this.bot=bot;
        emptyleaf = new EmptyLeaf(bot);
        ispathtomobnull = new AmIInZammyRoom(bot);
    }

    @Override
    public boolean validate() {
        return bot.getMonster().getIndex()==89;
    }

    @Override
    public TreeTask failureTask() {
        return emptyleaf;
    }

    @Override
    public TreeTask successTask() {
        return ispathtomobnull;
    }
}
