package com.ethan0pia.bots.WineGrabber.leaves;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawPreset extends LeafTask {

    private OpiaWineGrabber bot;
    private ActionBar.Slot food = null;
    private boolean checked = false;
    private ActionBar.Slot telegrabSpell = null;

    public WithdrawPreset(OpiaWineGrabber bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WithdrawPreset");
            if (!checked) {
                telegrabSpell = ActionBar.newQuery().names("Telekinetic Grab").results().first();
                food = ActionBar.newQuery().names(bot.getFoodType()).results().first();
                checked = true;
            }
            if (Bank.loadPreset(bot.getBankPreset())) {
                Execution.delayUntil(() -> !Bank.isOpen(), 500,2000);
                if (!Bank.isOpen()) {
                    bot.setBankBool(false);
                    bot.setRun(false);
                    Execution.delay(300,600);
                    if (!Inventory.contains(bot.getFoodType())) {
                        if(food==null || !food.isActivatable()) {
                            ClientUI.showAlert("Out of Food!");
                            Environment.getLogger().severe("Out of Food!");
                            Environment.getBot().stop("Out of Food!");
                        }
                    }else if(!Inventory.contains(563)){
                        if(telegrabSpell==null || !telegrabSpell.isActivatable()) {
                            ClientUI.showAlert("Out of Runes!");
                            Environment.getLogger().severe("Out of Runes!");
                            Environment.getBot().stop("Out of Runes!");
                        }
                    }
                    if(bot.isUseCam()) {
                        Camera.turnTo(28, Random.nextDouble(0.6,0.9), 0.1);
                    }else{
                        Camera.turnTo(bot.getCameraYaw(), bot.getCameraPitch(), 0.1);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
