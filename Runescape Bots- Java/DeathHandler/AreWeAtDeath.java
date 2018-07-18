package com.ethan0pia.bots.DeathHandler;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Region;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * This is the root node.
 * 
Add children of this branch using the settings to the right.
 */
public class AreWeAtDeath extends BranchTask {

    private EmptyLeaf emptyleaf = new EmptyLeaf();
    private RS3GrimReaper rs3GrimReaper = new RS3GrimReaper();

    @Override
    public boolean validate() {
        return isValid();
    }

    @Override
    public TreeTask failureTask() {
        return emptyleaf;
    }

    @Override
    public TreeTask successTask() {
        return rs3GrimReaper;
    }


    //@Override
    public boolean isValid() {
        return Region.isUnique() && getDeathEntity() != null;
    }
    private static GameObject getDeathEntity() {
        return GameObjects.newQuery().names("Death", "Frank").results().first();
    }
}
