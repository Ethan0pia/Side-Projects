package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.queries.InterfaceComponentQueryBuilder;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.rs3.local.hud.interfaces.MoneyPouch;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Attack the monster.
 */
public class AlchItem extends LeafTask {

    private OpiaSpiritualMages bot;
    private int alchCost = 0;
    private boolean magicLvl=false;
    private boolean firstChecks = true;
    private ActionBar.Slot ability;

    public AlchItem(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("AlchItem");
        bot.setCurrentTask("Alching Item");
        try {
            Player player = bot.getPlayer();
            SpriteItem itemToAlch = bot.getItemToAlch();
            if(firstChecks){
                magicLvl = Skill.MAGIC.getCurrentLevel() >= 55;
                int fireRunesCost = GrandExchange.lookup(554).getPrice();
                int natureRuneCost = GrandExchange.lookup(561).getPrice();
                alchCost = fireRunesCost * 5 + natureRuneCost;
                ability = ActionBar.newQuery().names("High Level Alchemy").results().first();
                firstChecks=false;
            }
            if (itemToAlch != null && itemToAlch.isValid()) {
                SpriteItem fireRunes = Inventory.getItems("Fire rune").first();
                if(fireRunes.getQuantity()>=5){
                    if (ability != null && ability.isActivatable()) {
                        if (ability.activate(false)) {
                            alch(itemToAlch, player);
                        } else if (ability.activate(true)) {
                            alch(itemToAlch, player);
                        } else if (ability.click()) {
                            alch(itemToAlch, player);
                        }
                    } else if (Inventory.contains("Nature rune") && magicLvl) {
                        if (Powers.Magic.HIGH_LEVEL_ALCHEMY.activate()) {
                            alch(itemToAlch, player);
                        }
                    }
                }else{
                    bot.setNeedBank(true);
                }
            } else {
                bot.removeItemToAlch();
            }
            bot.getUtils().stuckCheck(12);
        }catch(Exception e){
            Environment.getLogger().debug("Failed to alch item");
        }
    }

    //casts and waits
    private void alch(SpriteItem itemToAlch, Player player){
        itemToAlch.hover();
        if (itemToAlch.interact("Cast")) {
            Execution.delayUntil(() -> !itemToAlch.isValid(), 1500);
        }else{
            new Area.Circular(player.getPosition(),4).getRandomCoordinate().hover();
        }
        if (!itemToAlch.isValid()) {
            bot.setGpGained(bot.getGpGained() + bot.getAlchValue(itemToAlch.getId())-alchCost);
            bot.removeItemToAlch();
        }
    }
}
