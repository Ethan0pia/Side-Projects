package com.ethan0pia.bots.DuelSlave.branches;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.ethan0pia.bots.DuelSlave.leaves.WalkToDuelArena;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AmIAtDuelArenaLobbyArea extends BranchTask {

    private Area duelingLobby;
    private IsAnInterfaceOpen isduelinterfaceopen;
    private WalkToDuelArena walktoduelarena;
    private DuelingSlave bot;

    public AmIAtDuelArenaLobbyArea(DuelingSlave bot){
        this.bot=bot;
        isduelinterfaceopen = new IsAnInterfaceOpen(bot);
        walktoduelarena = new WalkToDuelArena(bot);
        duelingLobby = bot.getDuelingLobby();
    }

    @Override
    public boolean validate() {
        Player player = bot.getPlayer();
        if(player != null){
            if(duelingLobby.contains(player)){
                return true;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return walktoduelarena;
    }

    @Override
    public TreeTask successTask() {
        return isduelinterfaceopen;
    }
}
