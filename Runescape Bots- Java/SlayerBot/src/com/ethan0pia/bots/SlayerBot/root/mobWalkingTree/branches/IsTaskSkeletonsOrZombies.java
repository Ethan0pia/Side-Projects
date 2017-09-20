package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsTaskSkeletonsOrZombies extends BranchTask {

    private AmIByTrapdoorSkeletons amibytrapdoor;
    private IsTaskMinotaurs istaskminotaurs;
    private OpiaSlayer bot;

    public IsTaskSkeletonsOrZombies(OpiaSlayer bot){
        this.bot=bot;
        istaskminotaurs = new IsTaskMinotaurs(bot);
        amibytrapdoor = new AmIByTrapdoorSkeletons(bot);
    }


    @Override
    public boolean validate() {
        String mob = bot.getMonster().getMobName();
        return mob.equalsIgnoreCase("Skeleton")|| mob.equalsIgnoreCase("Zombie");
    }

    @Override
    public TreeTask failureTask() {
        return istaskminotaurs;
    }

    @Override
    public TreeTask successTask() {
        return amibytrapdoor;
    }
}
