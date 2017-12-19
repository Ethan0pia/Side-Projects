package com.ethan0pia.bots.wellBot.leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class ConfirmXandWait extends LeafTask {

    @Override
    public void execute() {
        GameObject bank = GameObjects.newQuery().names("Bank chest").within(new Area.Circular(Players.getLocal().getPosition(),8)).results().nearest();
        if(Keyboard.type("33", true)){
            if(bank!=null){
                bank.hover();
            }
            Execution.delayUntil(Inventory::isFull,2000);
        }
    }
}
