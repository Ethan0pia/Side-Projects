package com.ethan0pia.bots.wellBot.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.rs3.local.hud.interfaces.MakeXInterface;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class OpenBank extends LeafTask {

    @Override
    public void execute() {
        if(MakeXInterface.isOpen()){
            MakeXInterface.close();
        }
        if(Bank.open()){
            Execution.delayUntil(Bank::isOpen,800);
        }else{
            if(!Bank.open()) {
                Camera.turnTo(GameObjects.newQuery().names("Bank chest").results().nearest());
            }
        }
        if(Bank.isOpen()) {
            if(Keyboard.typeKey('1')){
                Execution.delayUntil(()->!Bank.isOpen(),800);
            }
        }
    }
}
