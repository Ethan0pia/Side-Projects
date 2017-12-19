package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AmIAtShadowWiz extends BranchTask {

    private AreThereItemsToLoot arethereitemstoloot = new AreThereItemsToLoot();
    private AmIPastWallBankSide amipastwall = new AmIPastWallBankSide();
    private Area mobs = new Area.Circular(new Coordinate(2909,3334,0),8);
    private Area mobs2 = new Area.Circular(new Coordinate(2909,3334,1),8);

    @Override
    public boolean validate() {
        if(mobs2.contains(Players.getLocal())){
            GameObject ladder = GameObjects.newQuery().actions("Climb-down").names("Staircase").results().nearest();
            if(ladder!=null){
                if(ladder.interact("Climb-down")){
                    Execution.delayUntil(()->mobs.contains(Players.getLocal()),2000);
                }
            }
        }
        return mobs.contains(Players.getLocal()) || mobs2.contains(Players.getLocal());
    }

    @Override
    public TreeTask failureTask() {
        return amipastwall;
    }

    @Override
    public TreeTask successTask() {
        return arethereitemstoloot;
    }
}
