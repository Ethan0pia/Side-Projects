package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.Smash;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsMobHealthLow extends BranchTask {

    private Smash smash;
    private IsAdrenalineAt100 isadrenalineat100;
    private GargSlayer bot;

    public IsMobHealthLow(GargSlayer bot){
        this.bot=bot;
        smash = new Smash(bot);
        isadrenalineat100 = new IsAdrenalineAt100(bot);
    }

    @Override
    public boolean validate() {
        Actor npc = bot.getPlayer().getTarget();
        return npc!=null && npc.getHealthGauge().getPercent()<12;
    }

    @Override
    public TreeTask failureTask() {
        return isadrenalineat100;
    }

    @Override
    public TreeTask successTask() {
        return smash;
    }
}
