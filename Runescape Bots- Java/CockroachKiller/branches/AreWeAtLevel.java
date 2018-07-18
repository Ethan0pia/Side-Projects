package com.ethan0pia.bots.CockroachKiller.branches;

import com.ethan0pia.bots.CockroachKiller.Roach;
import com.ethan0pia.bots.CockroachKiller.leaves.EmptyLeaf;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.concurrent.TimeUnit;

public class AreWeAtLevel extends BranchTask {

    private EmptyLeaf emptyLeaf;
    private DoWeHaveFood doWeHaveFood;
    private Roach bot;

    public AreWeAtLevel(Roach bot){
        this.bot=bot;
        doWeHaveFood = new DoWeHaveFood(bot);
        emptyLeaf = new EmptyLeaf(bot);
    }


    @Override
    public boolean validate() {
        Environment.getLogger().debug("3");
        switch(bot.getSkill()){
            case 0:
                return Skill.ATTACK.getBaseLevel()>=bot.getTarget();
            case 1:
                return Skill.MAGIC.getBaseLevel()>=bot.getTarget();
            default:
                return Skill.RANGED.getBaseLevel()>=bot.getTarget();
        }
    }

    @Override
    public TreeTask failureTask() {
        return doWeHaveFood;
    }

    @Override
    public TreeTask successTask() {
        GameEvents.Universal.LOGIN_HANDLER.disable();
        GameEvents.Universal.INTERFACE_CLOSER.disable();
        Execution.delayUntil(()->bot.getPlayer() != null && bot.getPlayer().getTarget() == null);
        if (bot.getStopWatch().getRuntime(TimeUnit.MINUTES) > 1) {
            if (bot.getPlayer() != null && bot.getPlayer().getTarget() == null) {
                GameEvents.Universal.INTERFACE_CLOSER.disable();
                if (Keyboard.typeKey(27)) {
                    InterfaceComponent exit = Interfaces.newQuery().textContains("Logout").results().first();
                    if (exit != null && exit.isVisible() && exit.click()) {
                        ClientUI.showAlert("[OpiaRoachKiller] Reached levels");
                        bot.stop("Reached levels");
                    }
                }
            }
        }
        return emptyLeaf;
    }
}
