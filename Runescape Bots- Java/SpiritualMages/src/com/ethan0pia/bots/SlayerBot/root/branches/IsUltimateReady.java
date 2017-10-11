package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.UseUltimate;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.List;

public class IsUltimateReady extends BranchTask {

    private IsThresholdReady isThresholdReady;
    private UseUltimate useUltimate;
    private List<ActionBar.Slot> abilityList;

    public IsUltimateReady(OpiaSpiritualMages bot){
        isThresholdReady = new IsThresholdReady(bot);
        useUltimate = new UseUltimate(bot);
    }

    @Override
    public boolean validate() {

        abilityList = ActionBar.newQuery().names("Balanced Strike",
                "Death's Swiftness","Frenzy","Incendiary Shot","Massacre","Metamorphosis","Meteor Strike","Omnipower",
                "Overpower","Pulverise","Sunshine","Tsunami","Unload").results().asList();

        return checkSlots(useUltimate.getUltimate1(),0) || checkSlots(useUltimate.getUltimate2(),1);
    }

    private boolean checkSlots(StopWatch stopwatch, int spot){
        if(stopwatch.getRuntime()>useUltimate.getRandomActivationTime()) {
            if (abilityList.size() > spot) {
                ActionBar.Slot ability;
                ability = abilityList.get(spot);
                useUltimate.setSlot(ability);
                useUltimate.setToActivate(spot + 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return isThresholdReady;
    }

    @Override
    public TreeTask successTask() {
        return useUltimate;
    }
}
