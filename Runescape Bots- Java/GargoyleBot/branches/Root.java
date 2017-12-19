package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class Root extends BranchTask {

    private EmptyLeaf emptyleaf;
    private IsHealthLow ishealthlow;


    private GargSlayer bot;

    public Root(GargSlayer bot){
        this.bot=bot;
        emptyleaf = new EmptyLeaf(bot);
        ishealthlow = new IsHealthLow(bot);
    }


    @Override
    public boolean validate() {
        bot.setPlayer(Players.getLocal());
        return bot.getGuiWait();
    }

    @Override
    public TreeTask failureTask() {
        return ishealthlow;
    }

    @Override
    public TreeTask successTask() {
        return emptyleaf;
    }
}
