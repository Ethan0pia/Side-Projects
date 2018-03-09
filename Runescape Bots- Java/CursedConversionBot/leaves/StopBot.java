package com.ethan0pia.bots.CursedConversionBot.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class StopBot extends LeafTask {

    @Override
    public void execute() {
        Environment.getBot().stop("Out of energy to convert.");
    }
}
