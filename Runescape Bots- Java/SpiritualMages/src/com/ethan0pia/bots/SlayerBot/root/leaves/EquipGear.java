package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
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
          Execution.delayUntil(()->!Bank.isOpen(),1000);
          bot.setGetFood(false);
          bot.setBankBool(true);
      }

        bot.getUtils().stuckCheck(5);
    }
}
