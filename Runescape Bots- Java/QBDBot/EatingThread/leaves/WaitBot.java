package com.ethan0pia.bots.QBDBot.EatingThread.leaves;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class WaitBot extends LeafTask {

    @Override
    public void execute() {
        Environment.getLogger().debug("EatingThread:WaitBot");
        Execution.delayUntil(()->
        {
            Player player = Players.getLocal();
            Area.Circular area = null;
            if(player!=null) {
                area = new Area.Circular(player.getPosition(),20);
            }
            return area!=null && !Npcs.newQuery().names("Queen Black Dragon").within(area).results().isEmpty();
            },1000,250000);
    }
}
