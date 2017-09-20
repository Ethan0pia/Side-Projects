package com.ethan0pia.bots.DuelSlave.branches;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.ethan0pia.bots.DuelSlave.leaves.ClickDuelOnPlayer;
import com.ethan0pia.bots.DuelSlave.leaves.WalkToCenter;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class CanISeePlayer extends BranchTask {

    private ClickDuelOnPlayer clickduelonplayer;
    private WalkToCenter walkToCenter;
    private DuelingSlave bot;

    public CanISeePlayer(DuelingSlave bot){
        this.bot=bot;
        clickduelonplayer = new ClickDuelOnPlayer(bot);
        walkToCenter = new WalkToCenter(bot);
    }

    @Override
    public boolean validate() {

        Player player = bot.getPlayer();
        Player opponent = Players.newQuery().names(bot.getOpponentsName()).results().first();
        return (player != null && opponent != null);
    }

    @Override
    public TreeTask failureTask() {
        return walkToCenter;
    }

    @Override
    public TreeTask successTask() {
        return clickduelonplayer;
    }
}
