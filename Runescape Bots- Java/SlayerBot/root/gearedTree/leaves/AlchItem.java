package com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.rs3.local.hud.interfaces.MoneyPouch;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class AlchItem extends LeafTask {

    private OpiaSlayer bot;
    private ActionBar.Slot slot;
    private int alchCost = 0;

    public AlchItem(OpiaSlayer bot){
        this.bot=bot;
    }

    public void setSlot(ActionBar.Slot slot) {
        this.slot = slot;
    }

    @Override
    public void execute() {
        if(alchCost==0){
            int fireRunesCost = GrandExchange.lookup(554).getPrice();
            int natureRuneCost = GrandExchange.lookup(561).getPrice();
            alchCost = fireRunesCost*5+natureRuneCost;
        }
        if(slot.getKeyBind()!=null) {
            slot.activate(false);
        }else{
            if(!slot.activate(true)){
                slot.activate(false);
            }
        }
        Execution.delay(1000,1500);
        if(!InterfaceWindows.getInventory().isOpen()){
            Execution.delay(500,1200);
            Keyboard.typeKey(66);
            Execution.delay(500,1200);
        }
        int coins = MoneyPouch.getContents();
        int itemPrice = bot.getValue(bot.getItemToAlch().getId());
        if(slot.isSelected()) {
            bot.getItemToAlch().click();
            Execution.delayUntil(()->MoneyPouch.getContents()>coins,3000);
        }
        int newCoinAmt = MoneyPouch.getContents();
        if(newCoinAmt!=coins){
            int gpGained = newCoinAmt-coins-alchCost-itemPrice;
            bot.setGpGained(bot.getGpGained()+gpGained);
        }
        bot.getUtils().stuckCheck(14);
    }
}
