package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EquipStaffFromInventory extends LeafTask {

    private OpiaSlayer bot;

    public EquipStaffFromInventory(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        SpriteItem staff = Inventory.newQuery().ids(772).results().first();
        if (staff != null) {
            if(Inventory.equip(staff)){
                Execution.delayUntil(() -> Equipment.contains(772), 1000, 3000);
            }
        }
        bot.getUtils().stuckCheck(39);
    }
}