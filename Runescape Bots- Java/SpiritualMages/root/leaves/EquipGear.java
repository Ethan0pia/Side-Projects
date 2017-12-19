package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EquipGear extends LeafTask {

    private OpiaSpiritualMages bot;

    public EquipGear(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
      if(Bank.loadPreset(1, false)){
          Execution.delayUntil(()->!Bank.isOpen(),2000);
          if(!Bank.isOpen()){
              if (!Inventory.contains("Beltfish") || !Inventory.contains("Law rune") || !Inventory.contains("Fire rune") || !Inventory.contains("Nature rune") || Equipment.getItemIn(Equipment.Slot.WEAPON.getIndex()) == null){
                  bot.stop("Out of materials");
              }else{
                  bot.setGetFood(false);
                  bot.setBankBool(true);
              }
          }
      }

        bot.getUtils().stuckCheck(7);
    }
}
