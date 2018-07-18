package com.ethan0pia.bots.DuelCap.branches;

import com.ethan0pia.bots.DuelCap.OpiaDuelCap;
import com.ethan0pia.bots.DuelCap.leaves.WalkToDuelArena;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeAtDuelArenaLobby extends BranchTask {

    private Area duelingLobby;
    private IsAnInterfaceOpen isduelinterfaceopen;
    private WalkToDuelArena walktoduelarena;
    private OpiaDuelCap bot;

    public AreWeAtDuelArenaLobby(OpiaDuelCap bot){
        this.bot=bot;
        isduelinterfaceopen = new IsAnInterfaceOpen(bot);
        walktoduelarena = new WalkToDuelArena(bot);
        duelingLobby = bot.getDuelingLobby();
    }

    @Override
    public boolean validate() {
        Player player = bot.getPlayer();
        return duelingLobby.contains(player);
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
