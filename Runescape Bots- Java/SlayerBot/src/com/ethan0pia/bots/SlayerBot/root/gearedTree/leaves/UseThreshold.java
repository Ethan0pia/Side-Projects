package com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class UseThreshold extends LeafTask {

    private OpiaSlayer bot;
    private ActionBar.Slot slot;
    private StopWatch threshold1 = new StopWatch();
    private StopWatch threshold2 = new StopWatch();
    private StopWatch threshold3 = new StopWatch();
    private int toActivate;
    private int randomActivationTime = Random.nextInt(20000,30000);

    public UseThreshold(OpiaSlayer bot){
        this.bot=bot;
        threshold1.start();
        threshold2.start();
        threshold3.start();
    }

    public void setSlot(ActionBar.Slot slot) {
        this.slot = slot;
    }

    public StopWatch getThreshold1() {
        return threshold1;
    }

    public StopWatch getThreshold2() {
        return threshold2;
    }

    public StopWatch getThreshold3() {
        return threshold3;
    }

    public void setToActivate(int toActivate) {
        this.toActivate = toActivate;
    }

    public int getRandomActivationTime() {
        return randomActivationTime;
    }

    @Override
    public void execute() {

        if(slot!=null){
            boolean activated;
            if(slot.getKeyBind()!=null) {
                if(!slot.activate(false)){
                    slot.activate(false);
                }
            }else{
                if(!slot.activate(true)){
                    slot.activate(true);
                }
            }
            activated=slot.isSelected();
            if(activated){
                switch(toActivate){
                    case 1:
                        threshold1.reset();
                        randomActivationTime = Random.nextInt(20000,30000);
                        break;
                    case 2:
                        threshold2.reset();
                        randomActivationTime = Random.nextInt(20000,30000);
                        break;
                    case 3:
                        threshold3.reset();
                        randomActivationTime = Random.nextInt(20000,30000);
                        break;
                }
            }
        }
        bot.getUtils().stuckCheck("r");
    }
}