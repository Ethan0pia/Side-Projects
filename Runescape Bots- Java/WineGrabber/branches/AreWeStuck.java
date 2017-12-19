package com.ethan0pia.bots.WineGrabber.branches;

import com.ethan0pia.bots.WineGrabber.OpiaWineGrabber;
import com.ethan0pia.bots.WineGrabber.leaves.Unstuck;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeStuck extends BranchTask {

    private InventoryNotFullAndContainLaws inventoryNotFullAndContainLaws;
    private Unstuck unstuck;
    private int leafId=0;
    private OpiaWineGrabber bot;
    private StopWatch stuckWatch = new StopWatch();

    public AreWeStuck(OpiaWineGrabber bot){
        this.bot=bot;
        inventoryNotFullAndContainLaws = new InventoryNotFullAndContainLaws(bot);
        unstuck = new Unstuck(bot);
    }


    @Override
    public boolean validate() {
        if(bot.getLeafId()==leafId) {
            if (stuckWatch.isRunning()) {
                if (stuckWatch.getRuntime() > 180000) {
                    return false;
                }
            } else {
                stuckWatch.start();
            }
        }else{
            stuckWatch.reset();
        }
        if(ChatDialog.getContinue()!=null){
            ChatDialog.getContinue().select(false);
        }
        return true;
    }

    @Override
    public TreeTask failureTask() {
        return unstuck;
    }

    @Override
    public TreeTask successTask() {
        return inventoryNotFullAndContainLaws;
    }
}
