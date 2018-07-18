package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.ethan0pia.bots.SpiritualMages.root.leaves.AlchItem;
import com.ethan0pia.bots.SpiritualMages.root.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES: done
 * Checks to see if we are at the monster
 */
public class ShouldWeAlch extends BranchTask {

    private EmptyLeaf emptyLeaf;
    private AlchItem alchItem;
    private OpiaSpiritualMages bot;

    public ShouldWeAlch(OpiaSpiritualMages bot){
        this.bot=bot;
        emptyLeaf = new EmptyLeaf(bot);
        alchItem = new AlchItem(bot);
    }

    @Override
    public boolean validate() {
        return bot.isToAlch();
    }

    @Override
    public TreeTask failureTask() {
        return emptyLeaf;
    }

    @Override
    public TreeTask successTask() {
        return alchItem;
    }
}
