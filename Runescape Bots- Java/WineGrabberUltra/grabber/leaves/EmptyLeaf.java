package com.ethan0pia.bots.WineGrabberUltra.grabber.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class EmptyLeaf extends LeafTask {

    private OpiaWineGrabberUltra bot;

    public EmptyLeaf(OpiaWineGrabberUltra bot) {
        this.bot=bot;
    }

    @Override
    public void execute() {

    }
}
