package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.entities.Item;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.rs3.queries.results.ActionBarQueryResults;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.List;

import static com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar.Slot.ContentType.ABILITY;

/**
 * NOTES:
 * checks if player has armor, weapon, and food.
 */
public class IsGeared extends BranchTask {

    private GoodAssSlayerBot Bot;

    public IsGeared(GoodAssSlayerBot bot){
        this.Bot=bot;
        requiresspecialitem = new RequiresSpecialItem(Bot);
        atbanknotgeared = new AtBankNotGeared(Bot);
    }

    private RequiresSpecialItem requiresspecialitem;
    private AtBankNotGeared atbanknotgeared;

    @Override
    public boolean validate() {
        int task = Varbits.load(7923).getValue();
        int combat = Bot.mobList.getCombatType(task);
        InterfaceComponent bar = Interfaces.newQuery().containers(1430).sprites(18199).types(InterfaceComponent.Type.SPRITE).results().first();
        int cbBar;
        switch(combat) {
            case 1:
                cbBar = Bot.meleeBar;
                break;
            case 2:
                cbBar = Bot.rangedBar;
                break;
            default:
                cbBar = Bot.magicBar;
                break;
        }

        if(ActionBar.getNumber() != cbBar) {
            if (bar != null && !Bank.isOpen()) {
                bar.interact(String.valueOf(cbBar));
                Execution.delayUntil(() -> ActionBar.getNumber() == cbBar, 2000, 2500);
            }
        }
        boolean runes;
        if(combat>2 && Inventory.contains(556)){
            runes=true;
        }
        else if(combat>2 && !Inventory.contains(556)){
            runes=false;
        }
        else{
            runes=true;
        }


        if(Inventory.contains(Bot.food)&& ActionBar.newQuery().results().first().isActivatable() && runes && (Inventory.contains(15707)||Equipment.contains(15707))) {
            return true;
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return atbanknotgeared;
    }

    @Override
    public TreeTask successTask() {
        return requiresspecialitem;
    }
}
