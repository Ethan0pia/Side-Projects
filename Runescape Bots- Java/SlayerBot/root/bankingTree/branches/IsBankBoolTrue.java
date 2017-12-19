package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks if player has banked since getting this task.
 */
public class IsBankBoolTrue extends BranchTask {

    private HasTheInventoryBeenEmptied hasTheInventoryBeenEmptied;
    private DoesInventoryContainAnything doesinventorycontainanything;
    private OpiaSlayer bot;

    public IsBankBoolTrue(OpiaSlayer bot){
        this.bot=bot;
        hasTheInventoryBeenEmptied = new HasTheInventoryBeenEmptied(bot);
        doesinventorycontainanything = new DoesInventoryContainAnything(bot);
    }

    @Override
    public boolean validate() {
        return bot.isBankBool();
    }

    @Override
    public TreeTask failureTask() {
        return doesinventorycontainanything;
    }

    @Override
    public TreeTask successTask() {
        return hasTheInventoryBeenEmptied;
    }
}
