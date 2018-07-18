package com.ethan0pia.bots.QBDBot.EatingThread.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class EatFood extends LeafTask {

    @Override
    public void execute() {
        Environment.getLogger().debug("EatingThread:EatFood");
        if(Keyboard.typeKey(';')) {
            Execution.delay(200, 300);
        }
    }
}
