package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class EmptyLeaf extends LeafTask {

    private OpiaWineGrabber bot;

    public EmptyLeaf(OpiaWineGrabber bot) {
        this.bot=bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("EmptyLeaf");
    }
}
