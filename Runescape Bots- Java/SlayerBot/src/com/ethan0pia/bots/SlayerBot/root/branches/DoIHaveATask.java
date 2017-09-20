package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches.AmIAtOldTask;
import com.runemate.game.api.hybrid.local.Varps;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks if player has a task.
 */
public class DoIHaveATask extends BranchTask {

    private IsAbilityBarSet isAbilityBarSet;
    private AmIAtOldTask amIAtOldTask;
    private OpiaSlayer bot;

    public DoIHaveATask(OpiaSlayer bot){
        this.bot=bot;
        isAbilityBarSet = new IsAbilityBarSet(bot);
        amIAtOldTask = new AmIAtOldTask(bot);

    }

    @Override
    public boolean validate() {
        return Varps.getAt(183).getValue()!=0;
    }

    @Override
    public TreeTask failureTask() {
        return amIAtOldTask;
    }

    @Override
    public TreeTask successTask() {
        return isAbilityBarSet;
    }
}
