package com.ethan0pia.bots.OsrsOrbMaker.branches;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.UseRoD;
import com.ethan0pia.bots.OsrsOrbMaker.leaves.StopBot;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class DoWeHaveRoDNeedTele extends BranchTask {

    private UseRoD useRoD;
    private StopBot walkedgeville;
    private OsrsOrbMaker bot;

    public DoWeHaveRoDNeedTele(OsrsOrbMaker bot){
        this.bot=bot;
        walkedgeville = null;
        useRoD = new UseRoD(bot);
    }

    @Override
    public boolean validate() {
        return Equipment.contains(2552) || Equipment.contains(2554) || Equipment.contains(2556) || Equipment.contains(2558) || Equipment.contains(2560) || Equipment.contains(2562)|| Equipment.contains(2564)|| Equipment.contains(2566);
    }

    @Override
    public TreeTask failureTask() {
        bot.stop("No Ring of Dueling found");
        return walkedgeville;
    }

    @Override
    public TreeTask successTask() {
        return useRoD;
    }
}
