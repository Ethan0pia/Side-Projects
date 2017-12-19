package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks to see if we need a special item for this monster. If we already have it, return false.
 */
public class DoINeedSpecialItem extends BranchTask {

    private DoesBankContainSpecialItem doesbankcontainspecialitem;
    private EmptyLeaf emptyLeaf;
    private OpiaSlayer bot;

    public DoINeedSpecialItem(OpiaSlayer bot){
        this.bot=bot;
        doesbankcontainspecialitem = new DoesBankContainSpecialItem(bot);
        emptyLeaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        int specialItem = bot.getMonster().getSpecialItem();
        return specialItem!=0 && !(Inventory.contains(specialItem) || Equipment.contains(specialItem) || Equipment.contains(bot.getUtils().alternativeSpecialsInBank()));
    }

    @Override
    public TreeTask failureTask() {
        return emptyLeaf;
    }

    @Override
    public TreeTask successTask() {
        return doesbankcontainspecialitem;
    }
}
