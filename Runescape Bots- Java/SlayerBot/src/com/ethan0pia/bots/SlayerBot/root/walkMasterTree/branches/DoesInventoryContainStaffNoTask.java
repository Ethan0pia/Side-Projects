package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.EquipStaffFromInventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class DoesInventoryContainStaffNoTask extends BranchTask {

    private EquipStaffFromInventory equipStaffFromInventory;
    private AmIAtBankNeedStaff amiatbankneedstaff;
    private OpiaSlayer bot;

    public DoesInventoryContainStaffNoTask(OpiaSlayer bot){
        this.bot=bot;
        amiatbankneedstaff = new AmIAtBankNeedStaff(bot);
        equipStaffFromInventory = new EquipStaffFromInventory(bot);
    }


    @Override
    public boolean validate() {
        return Inventory.contains(772);
    }

    @Override
    public TreeTask failureTask() {
        return amiatbankneedstaff;
    }

    @Override
    public TreeTask successTask() {
        return equipStaffFromInventory;
    }
}
