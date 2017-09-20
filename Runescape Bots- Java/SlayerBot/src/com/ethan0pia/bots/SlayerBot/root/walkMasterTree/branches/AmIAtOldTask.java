package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AmIAtOldTask extends BranchTask {

    private AmIAtMaster amIAtMaster;
    private com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches.AmITargetted AmITargetted;
    private OpiaSlayer bot;

    public AmIAtOldTask(OpiaSlayer bot){
        this.bot=bot;
        amIAtMaster = new AmIAtMaster(bot);
        AmITargetted = new AmITargetted(bot);
    }

    @Override
    public boolean validate() {
        Area mobArea = bot.getMonster().getMobArea();
        if(mobArea!=null && mobArea.contains(bot.getPlayer())){
            return true;
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return amIAtMaster;
    }

    @Override
    public TreeTask successTask() {
        return AmITargetted;
    }
}
