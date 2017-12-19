package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class SellGlories extends LeafTask {

    private OsrsOrbMaker bot;

    public SellGlories(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("SellGlories");


        if(Inventory.contains(1705)) {
            if (GrandExchange.isOpen()) {
                if (GrandExchange.newQuery().completed().results().isEmpty()) {
                    SpriteItem glories;
                    if(!Inventory.newQuery().ids(1705).results().isEmpty() && (glories = Inventory.newQuery().ids(1705).results().first())!=null && glories.click()){
                        Execution.delay(1000,2000);
                        InterfaceComponent down = Interfaces.newQuery().actions("-5%").results().first();
                        if(down!=null && down.click()){
                            Execution.delay(1000,2000);
                            InterfaceComponent confirm = Interfaces.newQuery().actions("Confirm").results().first();
                            if(confirm!=null && confirm.click()){
                                bot.setSoldGlories(true);
                                Execution.delay(1000,2000);
                            }
                        }
                    }
                } else if (!GrandExchange.newQuery().completed().results().isEmpty()) {
                    if (GrandExchange.collectToInventory()) {
                        Execution.delayUntil(() -> GrandExchange.newQuery().completed().results().isEmpty(), 2000);
                    }
                }
            } else {
                GrandExchange.open();
            }
        }else if(Bank.isOpen()){
            if(Bank.getWithdrawMode() != Bank.WithdrawMode.NOTE) {
                Bank.setWithdrawMode(Bank.WithdrawMode.NOTE);
            }else{
                Bank.withdraw("Amulet of glory",100);
            }
        }else{
            if(Bank.open()){
                Execution.delayUntil(Bank::isOpen,2000);
            }
        }
    }
}
