package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.leaves.EmptyLeaf;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class IsTaskBlueDragons extends BranchTask {

    private DoIHaveDustyKey doihavedustykey;
    private EmptyLeaf emptyleaf;
    private OpiaSlayer bot;

    public IsTaskBlueDragons(OpiaSlayer bot){
        this.bot=bot;
        emptyleaf = new EmptyLeaf(bot);
        doihavedustykey = new DoIHaveDustyKey(bot);
    }


    @Override
    public boolean validate() {
        return bot.getMonster().getMobName().equalsIgnoreCase("Blue dragon");
    }

    @Override
    public TreeTask failureTask() {
        return emptyleaf;
    }

    @Override
    public TreeTask successTask() {
        return doihavedustykey;
    }
}
