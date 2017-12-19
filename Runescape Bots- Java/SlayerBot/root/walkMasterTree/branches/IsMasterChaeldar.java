package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class IsMasterChaeldar extends BranchTask {

    private DoIHaveDramenEquipped doihavedramenequipped;
    private IsMasterVannaka ismastervannaka;
    private OpiaSlayer bot;

    public IsMasterChaeldar(OpiaSlayer bot){
        this.bot=bot;
        ismastervannaka = new IsMasterVannaka(bot);
        doihavedramenequipped = new DoIHaveDramenEquipped(bot);
    }


    @Override
    public boolean validate() {
        return bot.getMaster()==3;
    }

    @Override
    public TreeTask failureTask() {
        return ismastervannaka;
    }

    @Override
    public TreeTask successTask() {
        return doihavedramenequipped;
    }
}
