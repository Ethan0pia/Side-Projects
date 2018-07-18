package com.ethan0pia.bots.DuelCap.leaves;

import com.ethan0pia.bots.DuelCap.OpiaDuelCap;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class EmptyLeaf extends LeafTask {

    private OpiaDuelCap bot;

    public EmptyLeaf(OpiaDuelCap bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
    }
}
