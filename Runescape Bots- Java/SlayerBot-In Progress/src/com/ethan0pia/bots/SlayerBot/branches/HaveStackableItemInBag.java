package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.LootItem;

/**
 * NOTES:
 * Checks bag for same stackable.
 */
public class HaveStackableItemInBag extends BranchTask {

    private GoodAssSlayerBot Bot;

    public HaveStackableItemInBag(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private LootItem lootitem = new LootItem(Bot);
    private InCombatAtMob incombatatmob = new InCombatAtMob(Bot);

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return incombatatmob;
    }

    @Override
    public TreeTask successTask() {
        return lootitem;
    }
}
