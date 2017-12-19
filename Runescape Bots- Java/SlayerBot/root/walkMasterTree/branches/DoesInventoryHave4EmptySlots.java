package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.DepositInventoryWalkZanaris;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.EquipDramen;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoesInventoryHave4EmptySlots extends BranchTask {

    private EquipDramen equipdramen;
    private DepositInventoryWalkZanaris depositinventory;
    private OpiaSlayer bot;

    public DoesInventoryHave4EmptySlots(OpiaSlayer bot){
        this.bot=bot;
        depositinventory = new DepositInventoryWalkZanaris(bot);
        equipdramen = new EquipDramen(bot);
    }


    @Override
    public boolean validate() {
        return Inventory.getEmptySlots()>3;
    }

    @Override
    public TreeTask failureTask() {
        return depositinventory;
    }

    @Override
    public TreeTask successTask() {
        return equipdramen;
    }
}
