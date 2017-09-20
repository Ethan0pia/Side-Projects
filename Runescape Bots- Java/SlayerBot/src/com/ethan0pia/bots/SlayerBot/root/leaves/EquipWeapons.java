package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EquipWeapons extends LeafTask {

    OpiaSlayer bot;

    public EquipWeapons(OpiaSlayer bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        String[] gear = bot.getMonster().getGear();

        if(Equipment.contains(772)){
            SpriteItem staff = Equipment.newQuery().ids(772).results().first();
            if(staff.click()){
                Execution.delayUntil(()->Inventory.contains(772));
            }
        }

        if(gear[11]!=null) {
            SpriteItem weapon = Inventory.newQuery().names(gear[11]).results().first();
            if (weapon != null) {
                Inventory.equip(weapon);
                Execution.delayUntil(() -> Equipment.contains(gear[11]), 1000, 3000);
            }
        }

        if(gear[8]!=null){
            SpriteItem offHand = Inventory.newQuery().names(gear[8]).results().first();
            if (offHand != null) {
                Inventory.equip(offHand);
                Execution.delayUntil(()->Equipment.contains(gear[8]),1000,3000);
            }
        }
        bot.getUtils().stuckCheck("v");
    }
}
