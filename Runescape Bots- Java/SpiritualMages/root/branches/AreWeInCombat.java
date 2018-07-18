package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * Checks to see if we are at the monster
 */
public class AreWeInCombat extends BranchTask {

    private ShouldWeAlch shouldWeAlch;
    private AreItemsWorthLooting areItemsWorthLooting;
    private OpiaSpiritualMages bot;
    private Actor target;

    public AreWeInCombat(OpiaSpiritualMages bot){
        this.bot=bot;
        shouldWeAlch = new ShouldWeAlch(bot);
        areItemsWorthLooting = new AreItemsWorthLooting(bot);
    }

    @Override
    public boolean validate() {
        try {
            Player player = bot.getPlayer();
            if(target==null || !target.isValid() || player.getHealthGauge()==null) {
                target = player.getTarget();
            }
            return Execution.delayUntil(()->target != null && target.isValid() && target.getAnimationId()!=836,100,500);
        }catch(Exception e){
            Environment.getLogger().debug("Failed to check in combat.");
            return false;
        }
    }

    @Override
    public TreeTask failureTask() {
        return areItemsWorthLooting;
    }

    @Override
    public TreeTask successTask() {
        return shouldWeAlch;
    }
}
