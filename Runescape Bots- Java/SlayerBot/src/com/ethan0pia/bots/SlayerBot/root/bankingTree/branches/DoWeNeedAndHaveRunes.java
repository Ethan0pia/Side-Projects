package com.ethan0pia.bots.SlayerBot.root.bankingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.bankingTree.leaves.WithdrawRunes;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Checks if we need runes and if we have them in our bag.
 */
public class DoWeNeedAndHaveRunes extends BranchTask {

    private WithdrawRunes withdrawrunes;
    private DoWeHaveTheCorrectGear doWeHaveTheCorrectGear;
    private OpiaSlayer bot;

    public DoWeNeedAndHaveRunes(OpiaSlayer bot){
        this.bot=bot;
        withdrawrunes = new WithdrawRunes(bot);
        doWeHaveTheCorrectGear = new DoWeHaveTheCorrectGear(bot);
    }

    @Override
    public boolean validate() {
        Bank.contains("Air rune");
        boolean inventoryContains = Inventory.contains(556);
        boolean bankContains = Bank.contains(556);
        switch(bot.getMonster().getSpellType()){
            case "Air":
                if(!bankContains && !inventoryContains){
                    ClientUI.showAlert("Out of runes.");
                    Environment.getLogger().info("Out of runes.");
                    bot.stop();
                }
                if(!inventoryContains) {
                    return true;
                }
                break;
            case "Water":
                boolean inventoryContains2 = Inventory.contains(555);
                if(!bankContains && !inventoryContains && !Bank.contains(555) && !inventoryContains2){
                    ClientUI.showAlert("Out of runes.");
                    Environment.getLogger().info("Out of runes.");
                    bot.stop();
                }
                if(!inventoryContains || !inventoryContains2) {
                    return true;
                }
                break;
            case "Earth":
                boolean inventoryContains3 = Inventory.contains(557);
                if(!bankContains && !inventoryContains && !Bank.contains(557) && !inventoryContains3){
                    ClientUI.showAlert("Out of runes.");
                    Environment.getLogger().info("Out of runes.");
                    bot.stop();
                }
                if(!inventoryContains || !inventoryContains3) {
                    return true;
                }
                break;
            case "Fire":
                boolean inventoryContains4 = Inventory.contains(554);
                if(!bankContains && !inventoryContains && !Bank.contains(554) && !inventoryContains4){
                    ClientUI.showAlert("Out of runes.");
                    Environment.getLogger().info("Out of runes.");
                    bot.stop();
                }
                if(bot.isAlch()){
                    SpriteItem item = Inventory.newQuery().names("Fire rune").results().first();
                    if(item!=null && item.getQuantity()<1000){
                        return true;
                    }
                }
                if(!inventoryContains || !inventoryContains4) {
                    return true;
                }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return doWeHaveTheCorrectGear;
    }

    @Override
    public TreeTask successTask() {
        return withdrawrunes;
    }
}
