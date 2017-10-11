package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.EquipGear;
import com.ethan0pia.bots.SlayerBot.root.leaves.OpenBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsBankOpenGWD extends BranchTask {

    private EquipGear withdrawfirerunesandlawrunes;
    private OpenBank openbank;
    OpiaSpiritualMages bot;

    public IsBankOpenGWD(OpiaSpiritualMages bot){
        this.bot=bot;
        openbank = new OpenBank(bot);
        withdrawfirerunesandlawrunes = new EquipGear(bot);
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
        return withdrawfirerunesandlawrunes;
    }
}
