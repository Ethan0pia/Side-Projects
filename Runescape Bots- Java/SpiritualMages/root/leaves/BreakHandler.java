package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.concurrent.TimeUnit;

/**
 * NOTES: done
 * 
 */
public class BreakHandler extends LeafTask {

    private OpiaSpiritualMages bot;
    private int duration = 1;

    public BreakHandler(OpiaSpiritualMages bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("BreakHandler");
        bot.setCurrentTask("Taking a break");
        try {
            bot.getUtils().stuckCheck(25);
            bot.setCountItems(false);
            if (Health.getCurrentPercent() < bot.getWhenToEat()) {
                SpriteItem tablet = Inventory.getItems(bot.getTeleport()).first();//8010
                if (tablet != null) {
                    if (tablet.click()) {
                        Execution.delay(2000);
                    }
                }
            }

            GameEvents.Universal.LOBBY_HANDLER.disable();
            GameEvents.Universal.LOGIN_HANDLER.disable();

            if (bot.getPlayer() != null && bot.getPlayer().getTarget() == null) {
                if(duration>4 || bot.isStopBot()) {
                    GameEvents.Universal.INTERFACE_CLOSER.disable();
                    if (Keyboard.typeKey(27)) {
                        if (bot.isStopBot()) {
                            InterfaceComponent exit = Interfaces.newQuery().textContains("Logout").results().first();
                            if (exit != null && exit.isVisible() && exit.click()) {
                                ClientUI.showAlert("Stop time was reached.");
                                bot.stop("Stop time reached.");
                            }
                        } else {
                            InterfaceComponent exit = Interfaces.newQuery().textContains("Exit to Lobby").results().first();
                            if (exit != null && exit.isVisible() && exit.click()) {
                                Execution.delay(duration * 60000);
                            }
                        }
                    }
                    GameEvents.Universal.INTERFACE_CLOSER.enable();
                }else{
                    Execution.delayWhile(()->bot.getWhenToEat()>Health.getCurrentPercent(),100,duration * 60000);
                }
            }
        } catch(Exception e) {
            Environment.getLogger().debug("Failed to break correctly");
        }
    }

    public void setDuration(int dur){
        duration=dur;
    }
}