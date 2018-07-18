package com.ethan0pia.bots.DeathHandler;


import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.region.Region;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class Root extends BranchTask {

    private EmptyLeaf emptyleaf = new EmptyLeaf();
    private AreWeAtDeath areWeAtDeath = new AreWeAtDeath();

    @Override
    public boolean validate() {
        return Players.getLocal()==null;
    }

    @Override
    public TreeTask failureTask() {
        return areWeAtDeath;
    }

    @Override
    public TreeTask successTask() {
        return emptyleaf;
    }
}
