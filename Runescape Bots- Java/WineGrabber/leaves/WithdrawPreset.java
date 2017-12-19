package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawPreset extends LeafTask {

    private OpiaWineGrabber bot;

    public WithdrawPreset(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            if (Bank.isOpen()) {
                if (!Bank.contains(563) && !Inventory.contains(563)) {
                    Environment.getLogger().severe("Out of Runes!");
                    Environment.getBot().stop("Out of runes");
                }
            }
            if (Bank.loadPreset(1)) {
                Execution.delayUntil(() -> !Bank.isOpen(), 2000);
                if (!Bank.isOpen()) {
                    bot.setBankBool(false);
                    if (Inventory.newQuery().actions("Eat").results().isEmpty() || !Inventory.contains(563)) {
                        Environment.getLogger().severe("Out of Food or Law Runes!");
                        Environment.getBot().stop("Out of Food or Law Runes!");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        bot.setLeafId(6);
    }
}
