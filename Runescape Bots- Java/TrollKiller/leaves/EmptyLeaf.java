package com.ethan0pia.bots.TrollKiller.leaves;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EmptyLeaf extends LeafTask {

    private TrollKiller bot;

    public EmptyLeaf(TrollKiller bot){
        this.bot=bot;
    }


    @Override
    public void execute() {

    }
}
