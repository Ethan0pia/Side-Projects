package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.ethan0pia.bots.TrollKiller.leaves.AttackTroll;
import com.ethan0pia.bots.TrollKiller.leaves.EquipItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsSwordEquipped extends BranchTask {

    private EquipItem equipSword;
    private EquipItem equipOffhand;
    private AttackTroll attackTroll;
    private TrollKiller bot;
    private boolean mainHand;

    public IsSwordEquipped(TrollKiller bot){
        this.bot=bot;
        attackTroll = new AttackTroll(bot, "Troll chucker");
        equipSword = new EquipItem(bot,1277);
        equipOffhand = new EquipItem(bot,25710);
    }


    @Override
    public boolean validate() {
        if(!Equipment.contains(1277)){
            mainHand = true;
            return true;
        }else if(!Equipment.contains(25710) && Inventory.contains(25710)){
            mainHand = false;
            return true;
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return attackTroll;
    }

    @Override
    public TreeTask successTask() {
        if(mainHand) {
            return equipSword;
        }else{
            return equipOffhand;
        }
    }
}
