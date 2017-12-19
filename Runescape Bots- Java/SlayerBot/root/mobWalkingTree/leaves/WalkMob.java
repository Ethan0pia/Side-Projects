package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkMob extends LeafTask {

    private OpiaSlayer bot;

    public WalkMob(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(Bank.isOpen()&& Bank.close()){
            Execution.delayUntil(()->!Bank.isOpen(),2000);
        }
        bot.getUtils().walkPath(bot.getMonster().getMobArea().getCenter());
        bot.getUtils().stuckCheck(33);
    }
}
