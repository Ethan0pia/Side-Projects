package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves.LootItem;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInventoryFullOrContainsStackableAlreadyNoTask extends BranchTask {

    private AmIAtMaster amIAtMaster;
    private LootItem lootitem;
    private OpiaSlayer bot;
    private GroundItem item;

    public IsInventoryFullOrContainsStackableAlreadyNoTask(OpiaSlayer bot){
        this.bot=bot;
        amIAtMaster = new AmIAtMaster(bot);
        lootitem = new LootItem(bot);
    }

    public void setItem(GroundItem item) {
        this.item = item;
    }

    @Override
    public boolean validate() {
      return bot.getUtils().inventoryHaveRoom(item,lootitem);
    }

    @Override
    public TreeTask failureTask() {
        return lootitem;
    }

    @Override
    public TreeTask successTask() {
        return amIAtMaster;
    }
}

