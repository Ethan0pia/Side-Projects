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
        if(bot.getPlayer()!=null) {

            boolean runes;
            switch (bot.getMonster().getSpellType()) {
                case "Air":
                    runes = (Inventory.contains(556) && Inventory.getItems(556).first().getQuantity() > 5);
                    break;
                case "Water":
                    runes = (Inventory.contains(556) && Inventory.contains(555) && Inventory.getItems(556).first().getQuantity() > 5 && Inventory.getItems(555).first().getQuantity() > 5);
                    break;
                case "Earth":
                    runes = (Inventory.contains(556) && Inventory.contains(557) && Inventory.getItems(556).first().getQuantity() > 5 && Inventory.getItems(557).first().getQuantity() > 5);
                    break;
                case "Fire":
                    runes = (Inventory.contains(556) && Inventory.getItems(556).first().getQuantity() > 5 && Inventory.contains(554) && Inventory.getItems(554).first().getQuantity() > 5);
                    break;
                default:
                    runes = true;
                    break;
            }

            bot.getUtils().checkTorch();

            String ammunition = bot.getMonster().getGear()[10];
            if (ammunition != null && !ammunition.equalsIgnoreCase("")) {
                if (Equipment.getItemIn(Equipment.Slot.AMMUNITION.getIndex()) == null) {
                    return false;
                }
            }

            boolean abilityCheck = false;
            if (bot.getPlayer() != null && ActionBar.newQuery().results().first()!=null && ActionBar.newQuery().results().first().isActivatable()) {
                abilityCheck = true;
            }

            if (Inventory.isFull() && !Inventory.contains(bot.getMonster().getFoodType())) {
                bot.setGetFood(true);
                return false;
            }

            boolean quickTP = Inventory.contains(bot.getqTeleport());

            boolean specialCheck = true;
            String item = bot.getMonster().getSpecialItem();
            if (item != null) {
                specialCheck = Inventory.contains(item) || Equipment.contains(item);
                if(item.equalsIgnoreCase("Waterskin (4)")){
                    specialCheck=Inventory.contains(item)||Inventory.contains("Waterskin (3)")||Inventory.contains("Waterskin (2)")||Inventory.contains("Waterskin (1)");
                }
            }

            if(!ActionBar.isAutoRetaliating()){
                ActionBar.toggleAutoRetaliation();
            }

            return runes && quickTP && specialCheck && abilityCheck && !bot.isGetFood() && bot.isBankBool();
        }
        return false;
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
