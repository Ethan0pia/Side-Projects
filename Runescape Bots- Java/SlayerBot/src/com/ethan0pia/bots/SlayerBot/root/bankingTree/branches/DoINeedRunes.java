package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class DoINeedRunes extends BranchTask {

    private IsAlchemyEnabledBank isAlchemyEnabledBank;
    private DoWeHaveTheCorrectGear doWeHaveTheCorrectGear;
    private OpiaSlayer bot;

    public DoINeedRunes(OpiaSlayer bot){
        this.bot=bot;
        isAlchemyEnabledBank = new IsAlchemyEnabledBank(bot);
        doWeHaveTheCorrectGear = new DoWeHaveTheCorrectGear(bot);
    }

    @Override
    public boolean validate() {
        boolean runes;
        switch (bot.getMonster().getSpellType()) {
            case "Air":
                runes = !(Inventory.contains(556) && Inventory.getItems(556).first().getQuantity() > 900);
                break;
            case "Water":
                runes = !(Inventory.contains(556) && Inventory.contains(555) && Inventory.getItems(556).first().getQuantity() > 900 && Inventory.getItems(555).first().getQuantity() > 900);
                break;
            case "Earth":
                runes = !(Inventory.contains(556) && Inventory.contains(557) && Inventory.getItems(556).first().getQuantity() > 900 && Inventory.getItems(557).first().getQuantity() > 900);
                break;
            case "Fire":
                runes = !(Inventory.contains(556) && Inventory.getItems(556).first().getQuantity() > 900 && Inventory.contains(554) && Inventory.getItems(554).first().getQuantity() > 900);
                break;
            default:
                runes = bot.isAlch() && !(Inventory.contains(556) && Inventory.getItems(556).first().getQuantity() > 249 && Inventory.contains("Nature rune") && Inventory.getItems("Nature rune").first().getQuantity() > 49);
                break;
        }
        return runes;
    }


    @Override
    public TreeTask failureTask() {
        return doWeHaveTheCorrectGear;
    }

    @Override
    public TreeTask successTask() {
        return isAlchemyEnabledBank;
    }
}
