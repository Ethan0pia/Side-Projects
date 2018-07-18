package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.AttackGargoyle;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIInCombat extends BranchTask {

    private IsMobHealthLow ismobhealthlow;
    private AttackGargoyle attackgargoyle;

    private GargSlayer bot;

    public AmIInCombat(GargSlayer bot){
        this.bot=bot;
        ismobhealthlow = new IsMobHealthLow(bot);
        attackgargoyle = new AttackGargoyle(bot);
    }

    @Override
    public boolean validate() {
        InterfaceComponent healthGauge = Interfaces.newQuery().containers(1490).filter(i -> i.getId() == 97648660).results().first();
        Player player = bot.getPlayer();
        return !(player.getTarget() == null || healthGauge==null || !healthGauge.isVisible());
    }

    @Override
    public TreeTask failureTask() {
        return attackgargoyle;
    }

    @Override
    public TreeTask successTask() {
        return ismobhealthlow;
    }
}
