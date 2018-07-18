package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EmptyLeaf extends LeafTask {

    private OpiaWineGrabberUltra bot;

    public EmptyLeaf(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        Environment.getLogger().debug("EmptyLeaf");
        bot.setCurrentTask("Waiting...");
    }
}
