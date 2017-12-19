package com.ethan0pia.bots.DuelSlave.leaves;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EmptyLeaf extends LeafTask {

    private DuelingSlave bot;

    public EmptyLeaf(DuelingSlave bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
    }
}
