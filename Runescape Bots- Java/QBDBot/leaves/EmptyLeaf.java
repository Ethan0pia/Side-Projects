package com.ethan0pia.bots.QBDBot.leaves;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class EmptyLeaf extends LeafTask {
    private OpiaQBD bot;

    public EmptyLeaf(OpiaQBD bot){
        this.bot = bot;
    }

    @Override
    public void execute() {

    }
}
