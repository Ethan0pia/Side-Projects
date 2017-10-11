package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ethan0pia.bots.SlayerBot.root.leaves.AttackMob;

/**
 * NOTES: done
 * Checks if I am in combat.
 */
public class AmIInCombat extends BranchTask {

    private IsMobHPLow isTaskAStrykeWyrm;
    private AttackMob attackmob;
    private OpiaSpiritualMages bot;

    public AmIInCombat(OpiaSpiritualMages bot){
        this.bot=bot;
        attackmob = new AttackMob(bot);
        isTaskAStrykeWyrm = new IsMobHPLow(bot);
    }

    @Override
    public boolean validate() {
        return bot.getPlayer().getAnimationId()==-1;
    }

    @Override
    public TreeTask failureTask() {
        return isTaskAStrykeWyrm;
    }

    @Override
    public TreeTask successTask() {
        return attackmob;
    }
}
