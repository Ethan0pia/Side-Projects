package com.ethan0pia.bots.SpiritualMages.root.leaves;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class SetAbilityBar extends LeafTask {

    OpiaSpiritualMages bot;

    public SetAbilityBar(OpiaSpiritualMages bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {
        Environment.getLogger().debug("SetAbilityBar");
        bot.setCurrentTask("Setting Ability Bar");
        try {
            InterfaceComponent bar = Interfaces.newQuery().containers(1430).sprites(18199).types(InterfaceComponent.Type.SPRITE).results().first();
            int bcBar = bot.getCombatBar();
            if (ActionBar.getNumber() != bcBar) {
                if (bar != null) {
                    if (Bank.isOpen()) {
                        Bank.close();
                        Execution.delayUntil(() -> !Bank.isOpen(), 1000, 2000);
                    }
                    bar.interact(String.valueOf(bcBar));
                    Execution.delayUntil(() -> ActionBar.getNumber() == bcBar, 1000, 2500);
                }
            }
            bot.getUtils().stuckCheck(11);
        }catch(Exception e) {
            Environment.getLogger().debug("Failed to set ability bar");
        }
    }
}
