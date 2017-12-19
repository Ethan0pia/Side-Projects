package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.WithdrawQuickTeleport;

/**
 * NOTES: done
 * Checks to see if we have a ring of kinship equipped or in inventory
 */
public class DoesInventoryContainQuickTeleport extends BranchTask {

    private DoINeedRunes doINeedRunes;
    private WithdrawQuickTeleport withdrawQuickTeleport;
    private OpiaSlayer bot;

    public DoesInventoryContainQuickTeleport(OpiaSlayer bot){
        this.bot=bot;
        doINeedRunes = new DoINeedRunes(bot);
        withdrawQuickTeleport = new WithdrawQuickTeleport(bot);
    }

    @Override
    public boolean validate() {
        if (!Bank.contains(bot.getqTeleport()) && !Inventory.contains(bot.getqTeleport())) {
            Environment.getLogger().severe("Quick teleport not found. XD");
            bot.stop();
        }
        return Inventory.contains(bot.getqTeleport());

    }

    @Override
    public TreeTask failureTask() {
        return withdrawQuickTeleport;
    }

    @Override
    public TreeTask successTask() {
        return doINeedRunes;
    }
}
