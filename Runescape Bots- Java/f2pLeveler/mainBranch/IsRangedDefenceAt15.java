package com.ethan0pia.bots.f2pLeveler.mainBranch;

import com.ethan0pia.bots.f2pLeveler.cowKiller.branches.InventoryFull;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsRangedDefenceAt15 extends BranchTask {

        private InventoryFull inventoryFull= new InventoryFull();
        private IsAgilityAt5 isAgilityAt5= new IsAgilityAt5();

        @Override
        public TreeTask successTask() {
        return isAgilityAt5;
    }

        @Override
        public TreeTask failureTask() {
        return inventoryFull;
    }

        @Override
        public boolean validate() {
            return Skill.DEFENCE.getBaseLevel()>=30&&Skill.RANGED.getBaseLevel()>=30;
        }
}
