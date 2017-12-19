package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPlayerNull extends BranchTask {

    private IsHealthLow isHealthLow;
    private EmptyLeaf emptyLeaf;

    private OpiaWineGrabber bot;
    public IsPlayerNull(OpiaWineGrabber bot){
        this.bot=bot;
        isHealthLow = new IsHealthLow(bot);
        emptyLeaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        bot.setPlayer(Players.getLocal());
        return bot.getPlayer()!=null;
    }

    @Override
    public TreeTask failureTask() {
        return emptyLeaf;
    }

    @Override
    public TreeTask successTask() {
        return isHealthLow;
    }
}
