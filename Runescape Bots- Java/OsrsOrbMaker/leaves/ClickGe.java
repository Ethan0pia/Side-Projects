package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class ClickGe extends LeafTask {

    private OsrsOrbMaker bot;

    public ClickGe(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("ClickGe");
        if(GrandExchange.open()){
            Execution.delayUntil(GrandExchange::isOpen,2000);
        }
    }
}
