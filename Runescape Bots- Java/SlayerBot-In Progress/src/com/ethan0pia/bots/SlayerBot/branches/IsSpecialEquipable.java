package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Item;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.leaves.EquipSpecialItem;

/**
 * NOTES: done
 * Checks if can equip special item.
 */
public class IsSpecialEquipable extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsSpecialEquipable(GoodAssSlayerBot bot){
        Bot=bot;
        equipspecialitem = new EquipSpecialItem(Bot);
        isinventoryfullspecialitemnotequipable = new IsInventoryFullSpecialItemNotEquipable(Bot);
    }

    private EquipSpecialItem equipspecialitem;
    private IsInventoryFullSpecialItemNotEquipable isinventoryfullspecialitemnotequipable;

    @Override
    public boolean validate() {
        int task = Varbits.load(7923).getValue();
        return Bank.getItems(Bot.mobList.getSpecialItem(task)).first().getDefinition().isEquipable();
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
