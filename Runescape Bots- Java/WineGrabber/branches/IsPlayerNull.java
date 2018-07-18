package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPlayerNull extends BranchTask {

    private DoWeNeedToBank doWeNeedToBank;
    private EmptyLeaf emptyLeaf;

    private OpiaWineGrabber bot;
    public IsPlayerNull(OpiaWineGrabber bot){
        this.bot=bot;
        doWeNeedToBank = new DoWeNeedToBank(bot);
        emptyLeaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        Environment.getLogger().debug("IsPlayerNull");
        bot.setPlayer(Players.getLocal());
        return bot.getPlayer()!=null && bot.isGo();
    }

    @Override
    public TreeTask failureTask() {
        return emptyLeaf;
    }

    @Override
    public TreeTask successTask() {
        return doWeNeedToBank;
    }
}
