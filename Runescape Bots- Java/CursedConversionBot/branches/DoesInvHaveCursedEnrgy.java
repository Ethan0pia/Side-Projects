package com.ethan0pia.bots.CursedConversionBot.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.CursedConversionBot.leaves.ClickCursedEnrgy;
import com.ethan0pia.bots.CursedConversionBot.leaves.StopBot;

/**
 * NOTES:
 * 
 */
public class DoesInvHaveCursedEnrgy extends BranchTask {

    private ClickCursedEnrgy clickcursedenrgy = new ClickCursedEnrgy();
    private StopBot stopbot = new StopBot();

    @Override
    public boolean validate() {
        return Inventory.contains("Cursed energy") && Inventory.newQuery().names("Cursed energy").results().first().getQuantity()>99;
    }

    @Override
    public TreeTask failureTask() {
        return stopbot;
    }

    @Override
    public TreeTask successTask() {
        return clickcursedenrgy;
    }
}
