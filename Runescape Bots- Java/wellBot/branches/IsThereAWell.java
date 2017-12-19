package com.ethan0pia.bots.wellBot.branches;

import com.ethan0pia.bots.wellBot.leaves.UseWell;
import com.ethan0pia.bots.wellBot.leaves.WaitForWell;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class IsThereAWell extends BranchTask {

    private UseWell usewell = new UseWell();
    private WaitForWell waitforwell = new WaitForWell();

    @Override
    public boolean validate() {
        //return !GameObjects.newQuery().within(new Area.Circular(Players.getLocal().getPosition(),5)).names("Portable well").actions("Mix Potions").within(new Area.Circular(Players.getLocal().getPosition(),8)).results().isEmpty();
        return !GameObjects.newQuery().within(new Area.Circular(Players.getLocal().getPosition(),5)).names("Portable well").actions("Take Vials").within(new Area.Circular(Players.getLocal().getPosition(),8)).results().isEmpty();
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
