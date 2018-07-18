package com.ethan0pia.bots.QBDBot.EatingThread.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class DrinkPrayerPotion extends LeafTask {

    @Override
    public void execute() {
        Environment.getLogger().debug("EatingThread:DrinkPrayerPotion");
        if(Keyboard.typeKey('h')) {
            Execution.delay(200, 400);
        }
    }

}
