package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * 
 */
public class IsTaskMinotaurs extends BranchTask {

    private AmIOnFirstFloorMinotaurs amionfirstfloorminotaurs;
    private IsTaskInTaverlyDungeon istaskbluedragons;
    private OpiaSlayer bot;

    public IsTaskMinotaurs(OpiaSlayer bot){
        this.bot=bot;
        istaskbluedragons = new IsTaskInTaverlyDungeon(bot);
        amionfirstfloorminotaurs = new AmIOnFirstFloorMinotaurs(bot);
    }


    @Override
    public boolean validate() {
        return bot.getMonster().getIndex()==76;
    }

    @Override
    public TreeTask failureTask() {
        return istaskbluedragons;
    }

    @Override
    public TreeTask successTask() {
        return amionfirstfloorminotaurs;
    }
}
