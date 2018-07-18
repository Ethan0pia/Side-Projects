package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class UseQuickTeleport extends LeafTask {

    private OpiaSpiritualMages bot;

    public UseQuickTeleport(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("UseQuickTeleport");
        bot.setCurrentTask("Use Quick Teleport");
        try {
            Area.Circular spot = new Area.Circular(bot.getPlayer().getPosition(), 5);
            SpriteItem tablet = Inventory.getItems(bot.getTeleport()).first();//8010
            if (tablet != null) {
                if (tablet.click()) {
                    Execution.delayUntil(() -> !spot.contains(bot.getPlayer()), 4000, 10000);
                    if (!spot.contains(bot.getPlayer()) && bot.isStopBot()) {
                        GameEvents.Universal.INTERFACE_CLOSER.disable();
                        if (Keyboard.typeKey(27)) {
                            InterfaceComponent exit = Interfaces.newQuery().textContains("Logout").results().first();
                            if (exit != null && exit.isVisible() && exit.click()) {
                                Execution.delay(5000);
                            }
                        }
                        GameEvents.Universal.INTERFACE_CLOSER.enable();
                        GameEvents.Universal.LOBBY_HANDLER.disable();
                        GameEvents.Universal.LOGIN_HANDLER.disable();
                        ClientUI.showAlert("Stop time was reached.");
                        Environment.getBot().stop("Stop time reached.");
                    }
                }
            }
            bot.getUtils().stuckCheck(13);
        }catch(Exception e) {
            Environment.getLogger().debug("Failed to use quick teleport");
        }
    }
}
