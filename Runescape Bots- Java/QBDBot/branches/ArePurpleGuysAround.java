package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ArePurpleGuysAround extends BranchTask {

    private AreTheyNextToPlayer aretheynexttoplayer;
    private CanPillarsActivate canpillarsactivate;
    private OpiaQBD bot;

    public ArePurpleGuysAround(OpiaQBD bot){
        this.bot = bot;
        canpillarsactivate = new CanPillarsActivate(bot);
        aretheynexttoplayer = new AreTheyNextToPlayer(bot);
    }
    @Override
    public boolean validate() {
        Area.Circular area = new Area.Circular(bot.getPlayer().getPosition(),20);
        return !Npcs.newQuery().within(area).names("Tortured soul").results().isEmpty();
    }

    @Override
    public TreeTask failureTask() {
        return canpillarsactivate;
    }

    @Override
    public TreeTask successTask() {
        return aretheynexttoplayer;
    }
}
