package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.ethan0pia.bots.SlayerBot.leaves.LoadPreset;
import com.ethan0pia.bots.SlayerBot.leaves.WithdrawRunes;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class DoesNeedRunes extends BranchTask {
    private GoodAssSlayerBot Bot;

    public DoesNeedRunes(GoodAssSlayerBot bot){
        Bot=bot;
        loadPreset = new LoadPreset(Bot);
        withdrawRunes = new WithdrawRunes(Bot);
    }

    private LoadPreset loadPreset;
    private WithdrawRunes withdrawRunes;

    @Override
    public boolean validate() {
        int task = Varbits.load(7923).getValue();
        int combat = Bot.mobList.getCombatType(task);
        Bank.contains("Air rune");
        switch(combat){
            case 3:
                if(!Bank.contains(556)&& !Inventory.contains(556)){
                    System.out.println("No air runes.");
                    Bot.stop();
                }
                if(!Inventory.contains(556)) {
                    return true;
                }
            case 4:
                if(!Bank.contains(556)&& !Inventory.contains(556)){
                    System.out.println("No air runes.");
                    Bot.stop();
                }
                if(!Inventory.contains(556)) {
                    return true;
                }
                //Bank.withdraw("Water rune", 1000);
                break;
            case 5:
                if(!Bank.contains(556)&& !Inventory.contains(556)){
                    System.out.println("No air runes.");
                    Bot.stop();
                }
                if(!Inventory.contains(556)) {
                    return true;
                }
                //Bank.withdraw("Earth rune", 1000);
                break;
            case 6:
                if(!Bank.contains(556)&& !Inventory.contains(556)){
                    System.out.println("No air runes.");
                    Bot.stop();
                }
                if(!Inventory.contains(556)) {
                    return true;
                }
                //Bank.withdraw("Fire rune", 1000);
                break;

            }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return loadPreset;
    }

    @Override
    public TreeTask successTask() {
        return withdrawRunes;
    }
}

