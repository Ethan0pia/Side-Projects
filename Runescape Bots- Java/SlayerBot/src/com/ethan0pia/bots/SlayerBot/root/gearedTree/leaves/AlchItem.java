package com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class AlchItem extends LeafTask {

    private OpiaSlayer bot;
    private ActionBar.Slot slot;

    public AlchItem(OpiaSlayer bot){
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
            if(Keyboard.isPressed(18)){
                Keyboard.releaseKey(18);
            }
            Execution.delay(500,1200);
            Keyboard.typeKey(66);
            Execution.delay(500,1200);
        }

        if(slot.isSelected()) {
            bot.getItemToAlch().click();
            Execution.delay(1000,2000);
        }
        bot.getUtils().stuckCheck("n");
    }
}
