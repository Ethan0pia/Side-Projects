package com.ethan0pia.bots.TanBot.branches;

import com.ethan0pia.bots.TanBot.leaves.UseWell;
import com.ethan0pia.bots.TanBot.leaves.WaitForWell;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsThereACrafter extends BranchTask {

    private UseWell usewell = new UseWell();
    private WaitForWell waitforwell = new WaitForWell();

    @Override
    public boolean validate() {
        if(usewell.getCrafter()==null){
            usewell.setCrafter(GameObjects.newQuery().within(new Area.Circular(Players.getLocal().getPosition(),5)).names("Portable crafter").actions("Tan Leather").within(new Area.Circular(Players.getLocal().getPosition(),8)).results().nearest());
        }
        return usewell.getCrafter()!=null;
    }

    @Override
    public TreeTask failureTask() {
        return waitforwell;
    }

    @Override
    public TreeTask successTask() {
        return usewell;
    }
}
