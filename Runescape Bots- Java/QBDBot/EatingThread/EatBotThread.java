package com.ethan0pia.bots.QBDBot.EatingThread;


import com.ethan0pia.bots.QBDBot.EatingThread.branches.IsQbdAround;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class EatBotThread extends TreeBot {

    @Override
    public void onStart(String... args) {
        setLoopDelay(300, 500);
    }

    @Override
    public TreeTask createRootTask() {
        return new IsQbdAround();
    }
}
