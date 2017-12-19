package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES: done
 * 
 */
public class CloseBank extends LeafTask {

    private OpiaSpiritualMages bot;

    public CloseBank(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Bank.close();
        Execution.delayUntil(()->!Bank.isOpen(),1000,2000);

        bot.getUtils().stuckCheck(4);
    }
}
