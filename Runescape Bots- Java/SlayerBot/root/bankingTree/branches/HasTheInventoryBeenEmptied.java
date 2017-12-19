package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.DepositInventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasTheInventoryBeenEmptied extends BranchTask{

        private DepositInventory depositInventory;
        private DoesInventoryContainQuickTeleport doesInventoryContainQuickTeleport;
        private OpiaSlayer bot;

        public HasTheInventoryBeenEmptied(OpiaSlayer bot){
            this.bot=bot;
            depositInventory = new DepositInventory(bot);
            doesInventoryContainQuickTeleport = new DoesInventoryContainQuickTeleport(bot);
        }

        @Override
        public boolean validate() {
            return bot.isInventoryEmptied();
        }

        @Override
        public TreeTask failureTask() {
            return depositInventory;
        }

        @Override
        public TreeTask successTask() {
            return doesInventoryContainQuickTeleport;
        }
}
