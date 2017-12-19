package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.osrs.net.OSBuddyExchange;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class BuySomeGlories extends LeafTask {

    private OsrsOrbMaker bot;

    public BuySomeGlories(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("BuySomeGlories");
        if(GrandExchange.isOpen()) {
            if (GrandExchange.newQuery().completed().results().isEmpty()) {
                int numToBuy;
                int price = OSBuddyExchange.getGuidePrice(11978).getOverall();
                double priceBuy = price * 1.1;
                price = (int) priceBuy;
                SpriteItem coins = Inventory.newQuery().names("Coins").results().first();
                if(coins!=null) {
                    int coinCount = coins.getQuantity();
                    numToBuy = coinCount / price;
                    if (GrandExchange.isOpen()) {
                        if (GrandExchange.newQuery().completed().results().isEmpty() && GrandExchange.placeBuyOffer("Amulet of glory(6)", numToBuy, price)) {
                            if (Inventory.contains(11979)) {
                                bot.setBoughtGlories(true);
                            }
                            Execution.delayUntil(() -> !GrandExchange.newQuery().completed().results().isEmpty(), 2000);
                        } else if (!GrandExchange.newQuery().completed().results().isEmpty()) {
                            if (GrandExchange.collectToInventory()) {
                                Execution.delayUntil(() -> GrandExchange.newQuery().completed().results().isEmpty(), 2000);
                            }
                        }
                    }
                }
            }else{
                GrandExchange.collectToInventory();
                if (Inventory.contains(11979)) {
                    bot.setBoughtGlories(true);
                }
            }
        }else{
            GrandExchange.open();
        }
    }
}
