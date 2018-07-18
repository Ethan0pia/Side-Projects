package com.ethan0pia.bots.QBDBot.EatingThread.branches;

import com.ethan0pia.bots.QBDBot.EatingThread.leaves.WaitBot;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsQbdAround extends BranchTask {

    private IsHealthLow ishealthlow = new IsHealthLow();
    private WaitBot stopbot = new WaitBot();

    @Override
    public boolean validate() {
        Environment.getLogger().debug("EatingThread:IsQbdAround");
        return !Npcs.newQuery().names("Queen Black Dragon").results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return stopbot;
    }

    @Override
    public TreeTask successTask() {
        return ishealthlow;
    }
}
