package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.AlchIfNeeded;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.root.leaves.AttackMob;

/**
 * NOTES: done
 * Checks if I am in combat.
 */
public class AmIInCombat extends BranchTask {

    private AlchIfNeeded alchIfNeeded;
    private AttackMob attackmob;
    private OpiaSpiritualMages bot;

    public AmIInCombat(OpiaSpiritualMages bot){
        this.bot=bot;
        attackmob = new AttackMob(bot);
        alchIfNeeded = new AlchIfNeeded(bot);
    }

    @Override
    public boolean validate() {

        InterfaceComponent results = Interfaces.newQuery().containers(1490).filter(i->i.getId()==97648660).results().first();
        return bot.getPlayer().getTarget()!=null && results !=null && Integer.parseInt(results.getText())!=200;

    }

    @Override
    public TreeTask failureTask() {
        return attackmob;
    }

    @Override
    public TreeTask successTask() {
        return alchIfNeeded;
    }
}
