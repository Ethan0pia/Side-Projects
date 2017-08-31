package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.ethan0pia.bots.SlayerBot.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Does monster require a killing blow.
 */
public class RequiresBlow extends BranchTask {

    private GoodAssSlayerBot Bot;

    public RequiresBlow(GoodAssSlayerBot bot){
        Bot=bot;
        mobhpcheck = new MobHPCheck(Bot);
    }

    private MobHPCheck mobhpcheck;
	private EmptyLeaf empty = new EmptyLeaf();

    @Override
    public boolean validate() {

        int task = Varbits.load(7923).getValue();

        return Bot.mobList.getFinishingBlowName(task)!=null;
    }

    @Override
    public TreeTask failureTask() {
        return empty;
    }

    @Override
    public TreeTask successTask() {
        return mobhpcheck;
    }
}
