package com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class WalkBank extends LeafTask {
    private OpiaSlayer bot;

    public WalkBank(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(bot.getClosestBank()==null){
            bot.setClosestBank(bot.getUtils().closestBank());
        }
        Area bank = bot.getClosestBank();
        if(Camera.getPitch()<0.6){
            Camera.concurrentlyTurnTo(Random.nextDouble(0.6,0.9));
        }
        if(!bank.contains(bot.getPlayer())) {
            bot.getUtils().walkPath(bank.getCenter());
        }
        bot.getUtils().stuckCheck(8);
    }
}
