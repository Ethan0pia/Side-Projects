package com.ethan0pia.bots.TrollKiller.branches;

import com.ethan0pia.bots.TrollKiller.TrollKiller;
import com.ethan0pia.bots.TrollKiller.leaves.EmptyLeaf;
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

public class AreWeAtMagicLevel extends BranchTask {

    private EmptyLeaf emptyLeaf;
    private AreWeInWestRoom areWeInWestRoom;
    private TrollKiller bot;

    public AreWeAtMagicLevel(TrollKiller bot){
        this.bot=bot;
        areWeInWestRoom = new AreWeInWestRoom(bot);
        emptyLeaf = new EmptyLeaf(bot);
    }


    @Override
    public boolean validate() {
        return Skill.MAGIC.getBaseLevel()>=bot.getMagicTarget();
    }

    @Override
    public TreeTask failureTask() {
        return areWeInWestRoom;
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
                        ClientUI.showAlert("[OpiaTrollKiller] Reached levels");
                        bot.stop("Reached levels");
                    }
                }
            }
        }
        return emptyLeaf;
    }
}
