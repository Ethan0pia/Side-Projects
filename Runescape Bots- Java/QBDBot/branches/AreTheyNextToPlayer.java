package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.RunThroughPurpleGuys;
import com.ethan0pia.bots.QBDBot.leaves.TargetPurpleGuys;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class AreTheyNextToPlayer extends BranchTask {

    private RunThroughPurpleGuys runthroughpurpleguys;
    private TargetPurpleGuys targetpurpleguys;
    private OpiaQBD bot;

    public AreTheyNextToPlayer(OpiaQBD bot){
        this.bot = bot;
        runthroughpurpleguys = new RunThroughPurpleGuys(bot);
        targetpurpleguys = new TargetPurpleGuys(bot);
    }

    @Override
    public boolean validate() {
        Area.Circular area = new Area.Circular(bot.getPlayer().getPosition(),2);
        return !Npcs.newQuery().within(area).names("Tortured soul").results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return targetpurpleguys;
    }

    @Override
    public TreeTask successTask() {
        return runthroughpurpleguys;
    }
}
