package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * Talks to slayer master and gets task.
 */
public class GetTask extends LeafTask {

    private GoodAssSlayerBot Bot;

    public GetTask(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        Npc master = Npcs.getLoaded(Bot.master).nearest();
        if(master!=null){
            master.interact("Get-task", Bot.master);
            Execution.delayUntil(()-> Varbits.load(7917).getValue()!=0,2000,4000);
        }

    }
}
