package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.rs3.local.hud.interfaces.MoneyPouch;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Attack the monster.
 */
public class AttackMob extends LeafTask {

    private OpiaSpiritualMages bot;
    private Area mobArea = new Area.Circular(new Coordinate(2887,5358,0), 12);
    private int alchCost = 0;

    public AttackMob(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        InterfaceComponent healthGauge;
        Player player = bot.getPlayer();
        if(player.getTarget()==null && (healthGauge = Interfaces.newQuery().containers(1490).filter(i -> i.getId() == 97648660).results().first())!=null && healthGauge.getText().equals("200")) {
            Npc mob;
            if (player.getTarget() == null) {
                Npc targetMob = Npcs.newQuery().targeting(player).actions("Attack").within(new Area.Circular(player.getPosition(), 10)).names("Spiritual mage").filter(i -> (i.getSpotAnimationIds().isEmpty() || !(4100 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4200))).results().nearest();
                Npc nonTarget = Npcs.newQuery().targeting(player).actions("Attack").within(mobArea).filter(i -> (i.getSpotAnimationIds().isEmpty() || !(4100 <= i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4200))).results().nearest();
                if (targetMob != null && player.getTarget() == null) {
                    mob = targetMob;

                } else if (nonTarget != null) {
                    mob = nonTarget;

                } else {
                    mob = Npcs.newQuery().actions("Attack").names("Spiritual mage").within(mobArea).filter(i -> (i.getSpotAnimationIds().isEmpty() && i.getAnimationId() == -1) || (!(4180 < i.getSpotAnimationIds().get(0) && i.getSpotAnimationIds().get(0) < 4190) && i.getAnimationId() == -1 && i.getTarget() == null)).results().sortByDistance().limit(0, 2).random();//4187 is death spot animation
                }
                if (mob != null) {
                    if (!mob.isVisible()) {
                        Camera.turnTo(mob);
                    }
                    if (!player.isMoving()) {
                        mob.hover();
                        Execution.delay(100, 150);
                        if (mob.interact("Attack")) {
                            Execution.delay(500, 800);
                        }

                    }
                }
            }
        }

        if (bot.isToAlch()) {
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
        }

        Execution.delay(100,200);
        int whenToEat = bot.getWhenToEat();
        Execution.delayUntil(() -> player.getAnimationId() == -1 || Health.getCurrentPercent() < whenToEat, 5000);


        bot.getUtils().stuckCheck(2);
    }
}
