package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.rs3.local.hud.Powers;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class CastTrollheimTeleport extends LeafTask {

    private OpiaSlayer bot;

    public CastTrollheimTeleport(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        Powers.Magic.TROLLHEIM_TELEPORT.activate();
        bot.getUtils().stuckCheck(47);
    }
}
