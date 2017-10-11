package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.EquipGear;
import com.ethan0pia.bots.SlayerBot.root.leaves.OpenBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * 
 */
public class IsBankOpenNotGeared extends BranchTask {

    private EquipGear isBankBoolTrue;
    private OpenBank openbank;

    public IsBankOpenNotGeared(OpiaSpiritualMages bot){
        isBankBoolTrue = new EquipGear(bot);
        openbank = new OpenBank(bot);
    }

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return openbank;
    }

    @Override
    public TreeTask successTask() {
        return isBankBoolTrue;
    }
}
