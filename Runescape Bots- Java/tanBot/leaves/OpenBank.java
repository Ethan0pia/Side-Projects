package com.ethan0pia.bots.TanBot.leaves;

import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class OpenBank extends LeafTask {

    @Override
    public void execute() {
        if(Bank.open()){
            Execution.delayUntil(Bank::isOpen,600);
            Keyboard.typeKey("1");
        }else{
            if(!Bank.open()) {
                Camera.turnTo(GameObjects.newQuery().names("Bank chest").results().nearest());
            }
        }
    }
}
