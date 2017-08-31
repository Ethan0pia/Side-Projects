package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.WithdrawFood;

public class HaveFood extends BranchTask {
    private GoodAssSlayerBot Bot;

    public HaveFood(GoodAssSlayerBot bot){
        Bot=bot;
        runeCheck = new DoesNeedRunes(Bot);
        getFood = new WithdrawFood(Bot);
    }

    private DoesNeedRunes runeCheck;
    private WithdrawFood getFood;

    @Override
    public boolean validate() {
        return Inventory.contains(Bot.food);
    }

    @Override
    public TreeTask failureTask() {
        return getFood;
    }

    @Override
    public TreeTask successTask() {
        return runeCheck;
    }
}
