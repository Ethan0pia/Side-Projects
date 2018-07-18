package com.ethan0pia.bots.WineGrabberUltra.grabber.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.RunSafeSpot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DidWeGrabWine extends BranchTask {

    private RunSafeSpot runsafespot;
    private CanWeGrabMore canWeGrabMore;

    private OpiaWineGrabberUltra bot;

    public DidWeGrabWine(OpiaWineGrabberUltra bot){
        this.bot=bot;
        canWeGrabMore = new CanWeGrabMore(bot);
        runsafespot = new RunSafeSpot(bot);
    }

    @Override
    public boolean validate() {
        Player player = bot.getPlayer();
        if(player.getHealthGauge()!=null && bot.getTelegrabSpot().equals(player.getPosition())){
            bot.setRun(true);
        }
        return bot.isRun();
    }

    @Override
    public TreeTask failureTask() {
        return canWeGrabMore;
    }

    @Override
    public TreeTask successTask() {
        return runsafespot;
    }
}
