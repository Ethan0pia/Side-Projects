package com.ethan0pia.bots.CockroachKiller.branches;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.CockroachKiller.leaves.EmptyLeaf;
import com.ethan0pia.bots.CockroachKiller.leaves.EnterCrevice;
import com.ethan0pia.bots.CockroachKiller.leaves.WalkToCoords;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.concurrent.TimeUnit;

public class AreWeByCrevice extends BranchTask {

    private EnterCrevice enterCrevice;
    private WalkToCoords walkToCoords;
    private Roach bot;
    private Area.Circular crevice = new Area.Circular.Circular(new Coordinate(3077,3462,0),5);

    public AreWeByCrevice(Roach bot){
        this.bot=bot;
        walkToCoords = new WalkToCoords(bot, new Coordinate(3077,3462,0));
        enterCrevice = new EnterCrevice(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("4");
        return crevice.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return walkToCoords;
    }

    @Override
    public TreeTask successTask() {
        return enterCrevice;
    }
}
