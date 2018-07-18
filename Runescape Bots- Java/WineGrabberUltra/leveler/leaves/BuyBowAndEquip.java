package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class BuyBowAndEquip extends LeafTask {

    private OpiaWineGrabberUltra bot;
    private int loops = 0;

    public BuyBowAndEquip(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("BuyBowAndEquip");
            bot.setCurrentTask("Buying a bow from the shop.");
            if (loops >= 8) {
                if (Shop.close()) {
                    Execution.delayUntil(() -> !Shop.isOpen(), 2000);
                    if (!Shop.isOpen()) {
                        loops = 0;
                    }
                }
            } else {
                loops++;
                if (Inventory.contains(19830)) {
                    SpriteItem bow = Inventory.getItems(19830).first();
                    if (bow != null) {
                        if (bow.click()) {
                            Execution.delay(1000, 3000);
                        }
                    }
                } else {
                    InterfaceComponent chargeBow = Interfaces.newQuery().textContains("Chargebow").results().first();
                    if (chargeBow != null) {
                        if (chargeBow.click()) {
                            Execution.delay(100, 200);
                            InterfaceComponent take = Interfaces.newQuery().textContains("Take").results().first();
                            if (take != null) {
                                Execution.delay(100, 200);
                                if (take.click()) {
                                    Execution.delayUntil(() -> Inventory.contains(19830), 1000, 3000);
                                }
                            }

                        }
                    }
                }
            }
        }catch(Exception e){
                e.printStackTrace();
        }
    }
}
