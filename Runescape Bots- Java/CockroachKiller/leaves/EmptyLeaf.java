package com.ethan0pia.bots.CockroachKiller.leaves;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EmptyLeaf extends LeafTask {

    private Roach bot;

    public EmptyLeaf(Roach bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        Environment.getLogger().debug("11");
    }
}
