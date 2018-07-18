package com.ethan0pia.bots.QBDBot.EatingThread.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class DrinkStatPotion extends LeafTask {

    int potion;

    @Override
    public void execute() {
        Environment.getLogger().debug("EatingThread:DrinkStatPotion");
        switch(potion){
            case 1:
                if(Keyboard.typeKey('j')) {
                    Execution.delay(200, 300);
                }
                break;
            case 2:
                if(Keyboard.typeKey('k')) {
                    Execution.delay(200, 300);
                }
                break;
            case 3:
                if(Keyboard.typeKey('l')) {
                    Execution.delay(200, 300);
                }
                break;
        }
    }

    public void setPotion(int potion){
        this.potion = potion;
    }
}
