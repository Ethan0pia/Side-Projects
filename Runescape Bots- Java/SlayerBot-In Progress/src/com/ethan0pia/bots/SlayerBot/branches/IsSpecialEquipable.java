package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.EquipSpecialItem;

/**
 * NOTES:
 * Checks if can equip special item.
 */
public class IsSpecialEquipable extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsSpecialEquipable(GoodAssSlayerBot bot){
        Bot=bot;
    }

    private EquipSpecialItem equipspecialitem = new EquipSpecialItem(Bot);
    private IsInventoryFullSpecialItemNotEquipable isinventoryfullspecialitemnotequipable = new IsInventoryFullSpecialItemNotEquipable(Bot);

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return isinventoryfullspecialitemnotequipable;
    }

    @Override
    public TreeTask successTask() {
        return equipspecialitem;
    }
}
