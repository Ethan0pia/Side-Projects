package com.ethan0pia.bots.f2pLeveler.mainBranch;

import com.ethan0pia.bots.f2pLeveler.agility.AreWeAtAgilityCourse;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class IsAgilityAt5 extends BranchTask {

    private DoWeHaveGear dowehavelvl30gear = new DoWeHaveGear();
    private AreWeAtAgilityCourse areweatagilitycourse = new AreWeAtAgilityCourse();

    @Override
    public boolean validate() {
        return Skill.AGILITY.getBaseLevel()>=5;
    }

    @Override
    public TreeTask failureTask() {
        return areweatagilitycourse;
    }

    @Override
    public TreeTask successTask() {
        return dowehavelvl30gear;
    }
}
