package com.ethan0pia.bots.QBDBot.EatingThread.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class EmptyLeaf extends LeafTask {

    @Override
    public void execute() {
        Environment.getLogger().debug("EatingThread:EmptyLeaf");
        Execution.delay(200,500);
    }
}
