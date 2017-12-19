package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ItemsWorthPickingUp extends BranchTask {

    private IsInventoryFullOrContainsStackableAlreadyNoTask isinventoryfullorcontainsstackablealreadyNoTask;
    private AmIAtMaster amIAtMaster;
    private OpiaSlayer bot;
    private GroundItem oldItem;
    private int loops;

    public ItemsWorthPickingUp(OpiaSlayer bot){
        this.bot=bot;
        amIAtMaster = new AmIAtMaster(bot);
        isinventoryfullorcontainsstackablealreadyNoTask = new IsInventoryFullOrContainsStackableAlreadyNoTask(bot);
    }

    @Override
    public boolean validate() {
        if (bot.getPlayer() != null && bot.getMonster() != null && bot.getMonster().getMobArea() != null) {
            GroundItem list = bot.getUtils().checkGround();
            if (list != null) {
                if(oldItem==null){
                    oldItem=list;
                    loops=0;
                }
                if(oldItem.equals(list)|| oldItem==list){
                    loops++;
                    if(loops>8){
                        return false;
                    }else{
                        isinventoryfullorcontainsstackablealreadyNoTask.setItem(list);
                        return true;
                    }
                }else {
                    loops = 0;
                    isinventoryfullorcontainsstackablealreadyNoTask.setItem(list);
                    oldItem=null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return amIAtMaster;
    }

    @Override
    public TreeTask successTask() {
        return isinventoryfullorcontainsstackablealreadyNoTask;
    }
}

