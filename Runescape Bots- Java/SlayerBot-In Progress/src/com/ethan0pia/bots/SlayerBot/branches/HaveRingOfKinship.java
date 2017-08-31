package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.WithdrawRingOfKinship;

public class HaveRingOfKinship extends BranchTask {
    private GoodAssSlayerBot Bot;

    public HaveRingOfKinship(GoodAssSlayerBot bot){
        Bot=bot;
        withdrawRoK = new WithdrawRingOfKinship(Bot);
        haveFood = new HaveFood(Bot);
    }

    private WithdrawRingOfKinship withdrawRoK;
    private HaveFood haveFood;

    @Override
    public boolean validate() {
        Bank.contains(15707);
        if(!Bank.contains(15707) && !Equipment.contains(15707) && !Inventory.contains(15707)){
            System.out.println("Ring of kinship not found. XD");
            Bot.stop();
        }

        return Bank.contains(15707);

    }

    @Override
    public TreeTask failureTask() {
        return haveFood;
    }

    @Override
    public TreeTask successTask() {
        return withdrawRoK;
    }
}
