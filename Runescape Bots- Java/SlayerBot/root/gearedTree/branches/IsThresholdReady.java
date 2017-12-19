package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves.UseThreshold;
import com.ethan0pia.bots.SlayerBot.root.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import java.util.List;

public class IsThresholdReady extends BranchTask {

    private EmptyLeaf emptyLeaf;
    private UseThreshold useThreshold;
    private List<ActionBar.Slot> abilityList;

    public IsThresholdReady(OpiaSlayer bot){
        emptyLeaf = new EmptyLeaf(bot);
        useThreshold = new UseThreshold(bot);
    }

    @Override
    public boolean validate() {
        abilityList= ActionBar.newQuery().names("Asphyxiate", "Assault","Bombardment","Debilitate","Deep Impact","Destroy",
                "Flurry","Forceful Backhand","Hurricane","Quake","Rapid Fire","Slaughter","Snap Shot","Stomp","Tight Bindings","Wild Magic").results().asList();

        return checkSlots(useThreshold.getThreshold1(),0) || checkSlots(useThreshold.getThreshold2(),1)|| checkSlots(useThreshold.getThreshold3(),2);
    }

    private boolean checkSlots(StopWatch stopwatch, int spot){
        if(stopwatch.getRuntime()>useThreshold.getRandomActivationTime()) {
            if (abilityList.size() > spot) {
                ActionBar.Slot ability;
                ability = abilityList.get(spot);
                useThreshold.setSlot(ability);
                useThreshold.setToActivate(spot + 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return emptyLeaf;
    }

    @Override
    public TreeTask successTask() {
        return useThreshold;
    }
}