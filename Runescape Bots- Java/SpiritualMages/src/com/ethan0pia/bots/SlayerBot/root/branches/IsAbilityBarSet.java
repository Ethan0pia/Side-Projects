package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.SetAbilityBar;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAbilityBarSet extends BranchTask {

    private SetAbilityBar setAbilityBar;
    private AreWeGeared doesInventoryContainWeapons;
    private OpiaSpiritualMages bot;

    public IsAbilityBarSet(OpiaSpiritualMages bot){
        this.bot=bot;
        setAbilityBar = new SetAbilityBar(bot);
        doesInventoryContainWeapons = new AreWeGeared(bot);

    }

    @Override
    public boolean validate() {
        int bcBar=2;
        return ActionBar.getNumber()==bcBar;
    }

    @Override
    public TreeTask failureTask() {
        return setAbilityBar;
    }

    @Override
    public TreeTask successTask() {
        return doesInventoryContainWeapons;
    }
}