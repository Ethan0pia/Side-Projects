package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.EatFood;

/**
 * NOTES: done
 * Checks if player HP is higher than X% from GUI.
 */
public class PlayerHPCheck extends BranchTask {

    private GoodAssSlayerBot Bot;

    public PlayerHPCheck(GoodAssSlayerBot bot){
        Bot=bot;
        eatfood = new EatFood(Bot);
        requiresblow = new RequiresBlow(Bot);
    }

    private EatFood eatfood;
    private RequiresBlow requiresblow;

    @Override
    public boolean validate() {
        return Bot.player!=null && Health.getCurrentPercent()<50;
    }

    @Override
    public TreeTask failureTask() {
        return requiresblow;
    }

    @Override
    public TreeTask successTask() {
        return eatfood;
    }
}