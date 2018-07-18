package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class AlchItem extends LeafTask {

    private GargSlayer bot;
    private ActionBar.Slot slot;

    public AlchItem(GargSlayer bot){
        this.bot=bot;
    }

    public void setSlot(ActionBar.Slot slot) {
        this.slot = slot;
    }

    @Override
    public void execute() {

        if(slot.getKeyBind()!=null) {
            slot.activate(false);
        }else{
            slot.activate(true);
        }
        Execution.delay(1000,1500);
        if(!InterfaceWindows.getInventory().isOpen()){
            InterfaceWindows.getInventory().open();
            Execution.delay(500,1200);
        }

        if(slot.isSelected()) {
            bot.getItemToAlch().click();
            Execution.delay(1000,2000);
        }
    }
}
