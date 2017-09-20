package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsTaskOtherworldlyOrZygomites extends BranchTask {

    private DoIHaveDramenStaff doihavedramenstaff;
    private IsTaskFireGiantsOrBronzeDragons istaskfiregiants;
    private OpiaSlayer bot;

    public IsTaskOtherworldlyOrZygomites(OpiaSlayer bot){
        this.bot=bot;
        istaskfiregiants = new IsTaskFireGiantsOrBronzeDragons(bot);
        doihavedramenstaff = new DoIHaveDramenStaff(bot);
    }


    @Override
    public boolean validate() {
        String mob = bot.getMonster().getMobName();
        return mob.equalsIgnoreCase("Otherworldly being")||mob.equalsIgnoreCase("Mutated zygomite");
    }

    @Override
    public TreeTask failureTask() {
        return istaskfiregiants;
    }

    @Override
    public TreeTask successTask() {
        return doihavedramenstaff;
    }
}
