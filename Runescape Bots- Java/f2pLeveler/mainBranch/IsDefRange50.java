package com.ethan0pia.bots.f2pLeveler.mainBranch;

import com.ethan0pia.bots.f2pLeveler.StopBot;
import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches.DoesInventoryContainFood;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsDefRange50 extends BranchTask {

    private DoesInventoryContainFood doesInventoryContainFood= new DoesInventoryContainFood();
    private StopBot stopBot= new StopBot();

    @Override
    public TreeTask successTask() {
        return stopBot;
    }

    @Override
    public TreeTask failureTask() {
        return doesInventoryContainFood;
    }

    @Override
    public boolean validate() {
        return Skill.DEFENCE.getBaseLevel()>=50&&Skill.RANGED.getBaseLevel()>=50;
    }
}
