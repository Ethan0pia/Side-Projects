package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.leaves.EmptyLeaf;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves.DeliverKillingBlow;

/**
 * NOTES: done
 * Check if mob requires killing blow.
 */
public class DoesRequireKillingBlow extends BranchTask {

    private DeliverKillingBlow deliverkillingblow;
    private EmptyLeaf emptyleaf;
    private OpiaSlayer bot;

    public DoesRequireKillingBlow(OpiaSlayer bot){
        this.bot=bot;
        deliverkillingblow = new DeliverKillingBlow(bot);
        emptyleaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        return bot.getMonster().getFinishingBlowName()!=null;
    }

    @Override
    public TreeTask failureTask() {
        return emptyleaf;
    }

    @Override
    public TreeTask successTask() {
        return deliverkillingblow;
    }
}
