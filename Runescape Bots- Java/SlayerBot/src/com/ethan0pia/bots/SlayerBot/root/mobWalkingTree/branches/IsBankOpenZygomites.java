package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.OpenBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class IsBankOpenZygomites extends BranchTask {

    private DoesInventoryHave4EmptySlotsMobZanaris doesinventoryhave4emptyslots;
    private OpenBank openbank;

    public IsBankOpenZygomites(OpiaSlayer bot){
        openbank = new OpenBank(bot);
        doesinventoryhave4emptyslots = new DoesInventoryHave4EmptySlotsMobZanaris(bot);
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
        return doesinventoryhave4emptyslots;
    }
}
