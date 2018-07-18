package com.ethan0pia.bots.CockroachKiller.leaves;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawPreset extends LeafTask {

    private Roach bot;

    public WithdrawPreset(Roach bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("16");
            if(Bank.loadPreset(1)){
                Execution.delayWhile(Bank::isOpen,200,500);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
