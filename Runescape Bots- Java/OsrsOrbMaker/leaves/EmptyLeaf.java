package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EmptyLeaf extends LeafTask {

    private OsrsOrbMaker bot;

    public EmptyLeaf(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("EmptyLeaf");
    }
}
