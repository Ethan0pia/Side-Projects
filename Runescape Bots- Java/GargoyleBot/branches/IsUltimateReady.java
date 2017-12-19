package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.UseUltimate;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.List;

/**
 * NOTES:
 * 
 */
public class IsUltimateReady extends BranchTask {

    private UseUltimate useultimate;
    private IsThresholdReady isthresholdready;
    private List<ActionBar.Slot> abilityList;
    private GargSlayer bot;

    public IsUltimateReady(GargSlayer bot){
        isthresholdready = new IsThresholdReady(bot);
        useultimate = new UseUltimate(bot);
    }

    @Override
    public boolean validate() {

        abilityList = ActionBar.newQuery().names("Balanced Strike",
                "Death's Swiftness","Frenzy","Incendiary Shot","Massacre","Metamorphosis","Meteor Strike","Omnipower",
                "Overpower","Pulverise","Sunshine","Tsunami","Unload").results().asList();

        return checkSlots(useultimate.getUltimate1(),0) || checkSlots(useultimate.getUltimate2(),1);
    }

    private boolean checkSlots(StopWatch stopwatch, int spot){
        if(stopwatch.getRuntime()>useultimate.getRandomActivationTime()) {
            if (abilityList.size() > spot) {
                ActionBar.Slot ability;
                ability = abilityList.get(spot);
                useultimate.setSlot(ability);
                useultimate.setToActivate(spot + 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return isthresholdready;
    }

    @Override
    public TreeTask successTask() {
        return useultimate;
    }
}
