package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.gearedTree.branches.AmIAtBankGeared;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.branches.AmIAtBankNotGeared;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * check if inventory is full w/o food, special item, runes/arrows, and can combat stuff be used.
 */
public class AreWeGeared extends BranchTask {

    private AmIAtBankGeared amIAtBankGeared;
    private AmIAtBankNotGeared amiatbanknotgeared;
    private OpiaSlayer bot;

    public AreWeGeared(OpiaSlayer bot){
        this.bot=bot;
        amIAtBankGeared = new AmIAtBankGeared(bot);
        amiatbanknotgeared = new AmIAtBankNotGeared(bot);
    }

    @Override
    public boolean validate() {
        if(bot.getUtils().amIatBank()){
            if(!Inventory.contains(bot.getMonster().getFoodType())){
                return false;
            }else if(bot.isAlch() && !Inventory.contains(554) || !Inventory.contains(561)){
                return false;
            }
        }

        boolean runes;
        switch (bot.getMonster().getSpellType()) {
            case 1://air
                runes = (Inventory.contains(556) && Inventory.getItems(556).first().getQuantity() > 5);
                break;
            case 2://earth
                runes = (Inventory.contains(556) && Inventory.contains(557) && Inventory.getItems(556).first().getQuantity() > 5 && Inventory.getItems(557).first().getQuantity() > 5);
                break;
            case 3://water
                runes = (Inventory.contains(556) && Inventory.contains(555) && Inventory.getItems(556).first().getQuantity() > 5 && Inventory.getItems(555).first().getQuantity() > 5);
                break;
            case 4://fire
                runes = (Inventory.contains(556) && Inventory.getItems(556).first().getQuantity() > 5 && Inventory.contains(554) && Inventory.getItems(554).first().getQuantity() > 5);
                break;
            default:
                runes = true;
                break;
        }

        //relights torch if needed
        bot.getUtils().checkTorch();

        String ammunition = bot.getMonster().getGear()[9];
        if (ammunition != null && !ammunition.equalsIgnoreCase(" ") && !ammunition.equalsIgnoreCase("")) {
            if (Equipment.getItemIn(Equipment.Slot.AMMUNITION.getIndex()) == null) {
                return false;
            }
        }

        boolean abilityCheck = ActionBar.newQuery().results().first()!=null && ActionBar.newQuery().results().first().isActivatable();

        if (Inventory.isFull() && !Inventory.contains(bot.getMonster().getFoodType())) {
            bot.setGetFood(true);
            return false;
        }

        boolean quickTP = Inventory.contains(bot.getqTeleport());

        boolean specialCheck = true;
        int item = bot.getMonster().getSpecialItem();
        if (item != 0) {
            specialCheck = Inventory.contains(item) || Equipment.contains(item);
            if(item==1823){
                specialCheck=Inventory.contains(item)||Inventory.contains(1825)||Inventory.contains(1827)||Inventory.contains(1829);
            }
        }
        if(!specialCheck){
            specialCheck=bot.getUtils().alternativeSpecials();
        }

        if(!ActionBar.isAutoRetaliating()){
            ActionBar.toggleAutoRetaliation();
        }

        return runes && quickTP && specialCheck && abilityCheck && !bot.isGetFood() && bot.isBankBool();
    }

    @Override
    public TreeTask failureTask() {
        return amiatbanknotgeared;
    }

    @Override
    public TreeTask successTask() {
        return amIAtBankGeared;
    }

}
