package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.EquipGear;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks if special item can be equipped.
 */
public class IsSpecialEquippable extends BranchTask {

    private EquipGear equipGear;
    private IsInventoryFullNeedSpecial isinventoryfullneedspecial;
    private OpiaSlayer bot;

    public IsSpecialEquippable(OpiaSlayer bot){
        this.bot=bot;
        equipGear = new EquipGear(bot);
        isinventoryfullneedspecial = new IsInventoryFullNeedSpecial(bot);
    }

    @Override
    public boolean validate() {
        SpriteItem item = Bank.newQuery().ids(bot.getMonster().getSpecialItem()).results().first();
        if(item.getDefinition().isEquipable()){
            equipGear.setItem(item);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public TreeTask failureTask() {
        return isinventoryfullneedspecial;
    }

    @Override
    public TreeTask successTask() {
        return equipGear;
    }
}
