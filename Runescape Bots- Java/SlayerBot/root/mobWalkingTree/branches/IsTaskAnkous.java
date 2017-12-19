package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class IsTaskAnkous extends BranchTask {

    private AmIOnFourthFloorAnkous amionfourthfloorankous;
    private IsTaskShades istaskshades;
    private OpiaSlayer bot;

    public IsTaskAnkous(OpiaSlayer bot){
        this.bot=bot;
        istaskshades = new IsTaskShades(bot);
        amionfourthfloorankous = new AmIOnFourthFloorAnkous(bot);
    }


    @Override
    public boolean validate() {
        return bot.getMonster().getIndex()==79;
    }

    @Override
    public TreeTask failureTask() {
        return istaskshades;
    }

    @Override
    public TreeTask successTask() {
        return amionfourthfloorankous;
    }
}
