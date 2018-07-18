package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.LootChest;
import com.ethan0pia.bots.QBDBot.leaves.SendLootBank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsLootInventoryOpen extends BranchTask {

    private SendLootBank sendlootbank;
    private LootChest lootchest;
    private OpiaQBD bot;

    public IsLootInventoryOpen(OpiaQBD bot){
        this.bot = bot;
        lootchest = new LootChest(bot);
        sendlootbank = new SendLootBank(bot);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return lootchest;
    }

    @Override
    public TreeTask successTask() {
        return sendlootbank;
    }
}
