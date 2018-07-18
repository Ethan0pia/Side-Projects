package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.location.navigation.basic.ViewportPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class OpenBank extends LeafTask {

    private OpiaWineGrabber bot;
    private Coordinate tavBank = new Coordinate(2874,3417,0);
    private Coordinate falBank = new Coordinate(2946,3367,0);
    private Coordinate edgeBank = new Coordinate(3097,3495,0);

    public OpenBank(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("OpenBank");
            if (Bank.open()) {
                Execution.delayUntil(Bank::isOpen, 500,2000);
            } else if (!bot.getPlayer().isMoving()) {
                Environment.getLogger().debug("Bank did not open");
                switch(bot.getBankLocation()){
                    case 0:
                        Camera.concurrentlyTurnTo(falBank);
                        moveTowardsBank(falBank);
                        break;
                    case 1:
                        Camera.concurrentlyTurnTo(tavBank);
                        moveTowardsBank(tavBank);
                        break;
                    default:
                        Camera.concurrentlyTurnTo(edgeBank);
                        moveTowardsBank(edgeBank);
                        break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void moveTowardsBank(Coordinate bank){
        if (!bank.isVisible()) {
            BresenhamPath path = BresenhamPath.buildTo(bank);
            if(path!=null && ViewportPath.convert(path).step()) {
                Player player = bot.getPlayer();
                Execution.delayUntil(() -> player.isMoving() || player.getHealthGauge() != null, 500, 1000);
                Execution.delayWhile(() -> !bank.isVisible() && player.isMoving() && Health.getCurrentPercent() > 45, 500, 3000);
            }
        }
    }
}
