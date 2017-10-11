package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class UseUltimate extends LeafTask {

    private OpiaSpiritualMages bot;
    private ActionBar.Slot slot;
    private StopWatch ultimate1 = new StopWatch();
    private StopWatch ultimate2 = new StopWatch();
    private int toActivate;
    private int randomActivationTime = Random.nextInt(30000,45000);

    public UseUltimate(OpiaSpiritualMages bot){
        this.bot=bot;
        ultimate1.start();
        ultimate2.start();
    }

    public StopWatch getUltimate1() {
        return ultimate1;
    }

    public StopWatch getUltimate2() {
        return ultimate2;
    }

    public void setToActivate(int toActivate) {
        this.toActivate = toActivate;
    }

    public void setSlot(ActionBar.Slot slot) {
        this.slot = slot;
    }

    public int getRandomActivationTime() {
        return randomActivationTime;
    }

    @Override
    public void execute() {

        if (slot != null) {
            boolean activated;
            if (slot.getKeyBind() != null) {
                if(!slot.activate(false)){
                    slot.activate(false);
                }
            } else {
                if(!slot.activate(true)){
                    slot.activate(true);
                }
            }
            activated = slot.isSelected();
            if (activated) {
                switch (toActivate) {
                    case 1:
                        ultimate1.reset();
                        randomActivationTime = Random.nextInt(30000,45000);
                        break;
                    case 2:
                        ultimate2.reset();
                        randomActivationTime = Random.nextInt(30000,45000);
                        break;
                }
            }
        }
        bot.getUtils().stuckCheck(20);
    }
}
