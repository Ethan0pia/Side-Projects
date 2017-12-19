package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class OpenBank extends LeafTask {

    private OpiaWineGrabber bot;

    public OpenBank(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            GameObject bank;
            if (Bank.open()) {
                Execution.delayUntil(Bank::isOpen, 2000);
            } else if ((bank = GameObjects.newQuery().names("Bank booth").results().nearest()) != null && !bank.isVisible() && !bot.getPlayer().isMoving()) {
                Camera.concurrentlyTurnTo(bank);
                Execution.delayUntil(bank::isVisible, 2000);
                if (!bank.isVisible()) {
                    BresenhamPath path = BresenhamPath.buildTo(bank);
                    if (path.step()) {
                        Player player = bot.getPlayer();
                        Execution.delayUntil(() -> player.isMoving() || player.getHealthGauge() != null, 1000);
                        Execution.delayWhile(() -> !bank.isVisible() && player.isMoving() && Health.getCurrentPercent() > 40, 3000);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        bot.setLeafId(2);
    }
}
