package com.ethan0pia.bots.f2pLeveler;

import com.ethan0pia.bots.f2pLeveler.mainBranch.IsRangedDefenceAt15;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class F2pLeveler extends TreeBot {

    private StopWatch stopWatch = new StopWatch();

    @Override
    public TreeTask createRootTask()
    {
        // Return our root tree branch
        return new IsRangedDefenceAt15();
    }

    @Override
    public void onStart(String... args){
        stopWatch.start();
        setLoopDelay(1000, 2000);
    }

}