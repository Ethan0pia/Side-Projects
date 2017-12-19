package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Are items on the ground worth X or are noteable/stackable
 */
public class AreItemsOnGroundWorthX extends BranchTask {

    private IsInventoryFullOrContainsStackableAlready isinventoryfullorcontainsstackablealready;
    private AmIInCombat amIInCombat;
    private OpiaSlayer bot;
    private GroundItem oldItem;
    private int loops;


    public AreItemsOnGroundWorthX(OpiaSlayer bot){
        this.bot=bot;
        amIInCombat = new AmIInCombat(bot);
        isinventoryfullorcontainsstackablealready = new IsInventoryFullOrContainsStackableAlready(bot);
        loops=0;
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
                        if(loops==8) {
                            Environment.getLogger().debug("Failed to pick up item.");
                        }
                        if(loops>8){
                           return false;
                        }else{
                            isinventoryfullorcontainsstackablealready.setItem(list);
                            return true;
                        }
                    }else {
                        loops = 0;
                        isinventoryfullorcontainsstackablealready.setItem(list);
                        oldItem=null;
                        return true;
                    }
                }
            }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return amIInCombat;
    }

    @Override
    public TreeTask successTask() {
        return isinventoryfullorcontainsstackablealready;
    }
}
