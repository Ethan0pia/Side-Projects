package com.ethan0pia.bots.CursedConversionBot.branches;

import com.ethan0pia.bots.CursedConversionBot.CursedConversionBot;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.CursedConversionBot.leaves.EmptyLeaf;

/**
 * NOTES:
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class Root extends BranchTask {

    private EmptyLeaf emptyleaf = new EmptyLeaf();
    private IsMakeWindowOpen ismakewindowopen;
    private CursedConversionBot bot;

    public Root(CursedConversionBot bot){
        ismakewindowopen = new IsMakeWindowOpen(bot);
        this.bot=bot;
    }

    @Override
    public boolean validate() {
        bot.setPlayer(Players.getLocal());
        return bot.isGuiWait() || bot.getPlayer()==null || bot.getEnergy()==null;
    }

    @Override
    public TreeTask failureTask() {
        return ismakewindowopen;
    }

    @Override
    public TreeTask successTask() {
        return emptyleaf;
    }
}
