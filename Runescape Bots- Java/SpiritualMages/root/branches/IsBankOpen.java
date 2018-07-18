package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.ethan0pia.bots.SpiritualMages.root.leaves.EquipGear;
import com.ethan0pia.bots.SpiritualMages.root.leaves.OpenBank;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * 
 */
public class IsBankOpen extends BranchTask {

    private EquipGear equipGear;
    private OpenBank openbank;

    public IsBankOpen(OpiaSpiritualMages bot){
        equipGear = new EquipGear(bot);
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
        return equipGear;
    }
}
