package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.rs3.local.hud.interfaces.MoneyPouch;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class AlchIfNeeded extends LeafTask {

    private OpiaSpiritualMages bot;
    private int alchCost = 0;

    public AlchIfNeeded(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        boolean wait = false;
        if (bot.isToAlch() && bot.getItemToAlch()!=null && bot.getItemToAlch().isValid()) {
            SpriteItem fireRunes = Inventory.getItems("Fire rune").first();
            if (fireRunes != null && fireRunes.getQuantity() >= 5 && Inventory.contains("Nature rune") && Skill.MAGIC.getCurrentLevel() >= 55) {
                ActionBar.Slot ability = ActionBar.newQuery().names("High Level Alchemy").results().first();
                if(ability!=null){
                    if(alchCost==0){
                        int fireRunesCost = GrandExchange.lookup(554).getPrice();
                        int natureRuneCost = GrandExchange.lookup(561).getPrice();
                        alchCost = fireRunesCost*5+natureRuneCost;
                    }

                    if(!InterfaceWindows.getInventory().isOpen()){
                        InterfaceWindows.getInventory().open();
                        Execution.delayUntil(()->InterfaceWindows.getInventory().isOpen(),1200);
                    }
                    int coins = MoneyPouch.getContents();
                    int itemPrice = bot.getValue(bot.getItemToAlch().getId());

                    if(ability.activate(false)){
                        Execution.delay(125,175);
                        if(bot.getItemToAlch().click()){
                            Execution.delayUntil(()->MoneyPouch.getContents()>coins,1500);
                            wait = true;
                        }
                    }

                    int newCoinAmt = MoneyPouch.getContents();
                    if(newCoinAmt!=coins){
                        int gpGained = newCoinAmt-coins-alchCost-itemPrice;
                        bot.setGpGained(bot.getGpGained()+gpGained);
                        bot.removeItemToAlch();
                    }
                    bot.getUtils().stuckCheck(14);
                }
            }
        }else{
            wait = true;
        }
        if(wait) {
            int whenToEat = bot.getWhenToEat();
            Execution.delay(200,300);
            Execution.delayUntil(() -> bot.getPlayer().getAnimationId() == -1 || Health.getCurrentPercent() < whenToEat, 5000);
        }
        bot.getUtils().stuckCheck(1);
    }
}
