package com.ethan0pia.bots.SlayerBot.leaves;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


/**
 * NOTES: done
 * clicks preset based on monster weakness.
 */
public class LoadPreset extends LeafTask {

    private GoodAssSlayerBot Bot;

    public LoadPreset(GoodAssSlayerBot bot){
        Bot=bot;
    }

    @Override
    public void execute() {
        InterfaceComponent bar = Interfaces.newQuery().containers(1430).sprites(18199).types(InterfaceComponent.Type.SPRITE).results().first();

        int task = Varbits.load(7923).getValue();
        int combat = Bot.mobList.getCombatType(task);

        if(!Bank.contains(Bot.food)){
            System.out.println("Correct food not in bank.");
            Bot.stop();
        }

        int cbBar;
        switch(combat){
            case 1:
                Bank.loadPreset(Bot.meleePreset);
                cbBar=Bot.meleeBar;
                break;
            case 2:
                Bank.loadPreset(Bot.rangedPreset);
                cbBar=Bot.rangedBar;
                break;
            default:
                Bank.loadPreset(Bot.magicPreset);
                cbBar=Bot.magicBar;
                break;
           /* case 4:
                cbBar=Bot.magicBar;
                Bank.loadPreset(Bot.magicPreset);
                break;
            case 5:
                cbBar=Bot.magicBar;
                Bank.loadPreset(Bot.magicPreset);
                break;
            default:
                cbBar=Bot.magicBar;
                Bank.loadPreset(Bot.magicPreset);
                break;*/
        }

        if(bar != null && !Bank.isOpen()) {
            if(cbBar != ActionBar.getNumber()) {
                bar.interact(String.valueOf(cbBar));
                Execution.delayUntil(() -> ActionBar.getNumber() == cbBar, 1200, 1800);
            }
        }
    }
}
