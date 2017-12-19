package com.ethan0pia.bots.telegrabLeveler.leaves;

import com.ethan0pia.bots.telegrabLeveler.TelegrabLeveler;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EmptyLeaf extends LeafTask {

    private TelegrabLeveler bot;

    public EmptyLeaf(TelegrabLeveler bot){
        this.bot=bot;
    }


    @Override
    public void execute() {

    }
}
