package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class DoIHaveDramenEquipped extends BranchTask {

    private AmIAtLumbHouse amiatlumbhouse;
    private DoesInventoryContainStaffNoTask doesInventoryContainStaffNoTask;
    private OpiaSlayer bot;

    public DoIHaveDramenEquipped(OpiaSlayer bot){
        this.bot=bot;
        doesInventoryContainStaffNoTask = new DoesInventoryContainStaffNoTask(bot);
        amiatlumbhouse = new AmIAtLumbHouse(bot);
    }


    @Override
    public boolean validate() {
        return Equipment.contains(772);
    }

    @Override
    public TreeTask failureTask() {
        return doesInventoryContainStaffNoTask;
    }

    @Override
    public TreeTask successTask() {
        return amiatlumbhouse;
    }
}
