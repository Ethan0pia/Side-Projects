package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsTaskShades extends BranchTask {

    private AmIOnTheThirdFloorAnkous amionthethirdfloorankous;
    private IsTaskOtherworldlyOrZygomites istaskotherworldlyorzygomites;
    private OpiaSlayer bot;

    public IsTaskShades(OpiaSlayer bot){
        this.bot=bot;
        istaskotherworldlyorzygomites = new IsTaskOtherworldlyOrZygomites(bot);
        amionthethirdfloorankous = new AmIOnTheThirdFloorAnkous(bot);
    }


    @Override
    public boolean validate() {
        return bot.getMonster().getMobName().equalsIgnoreCase("Shade");
    }

    @Override
    public TreeTask failureTask() {
        return istaskotherworldlyorzygomites;
    }

    @Override
    public TreeTask successTask() {
        return amionthethirdfloorankous;
    }
}
