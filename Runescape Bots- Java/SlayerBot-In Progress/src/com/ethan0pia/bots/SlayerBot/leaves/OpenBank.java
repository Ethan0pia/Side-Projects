package com.ethan0pia.bots.SlayerBot.leaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class OpenBank extends LeafTask {

    @Override
    public void execute() {
        Bank.open();
    }
}
