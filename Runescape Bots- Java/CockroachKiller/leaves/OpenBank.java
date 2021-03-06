package com.ethan0pia.bots.CockroachKiller.leaves;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class OpenBank extends LeafTask {

    private Roach bot;
    private Coordinate bankSpot = new Coordinate(3094,3491,0);

    public OpenBank(Roach bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("14");
            if (Bank.open()) {
                Execution.delayUntil(Bank::isOpen, 500,2000);
            } else if (!bot.getPlayer().isMoving()) {
                Camera.concurrentlyTurnTo(bankSpot);
                if (!bankSpot.isVisible()) {
                    BresenhamPath path = BresenhamPath.buildTo(bankSpot);
                    if(path!=null && ViewportPath.convert(path).step()) {
                        Player player = bot.getPlayer();
                        Execution.delayUntil(() -> player.isMoving() || player.getHealthGauge() != null, 500, 1000);
                        Execution.delayWhile(() -> !bankSpot.isVisible() && player.isMoving() && Health.getCurrentPercent() > 45, 500, 3000);
                    }
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
