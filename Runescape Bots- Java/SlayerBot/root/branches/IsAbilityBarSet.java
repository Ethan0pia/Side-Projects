package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.leaves.SetAbilityBar;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAbilityBarSet extends BranchTask {

    private SetAbilityBar setAbilityBar;
    private DoesInventoryContainWeapons doesInventoryContainWeapons;
    private OpiaSlayer bot;

    public IsAbilityBarSet(OpiaSlayer bot){
        this.bot=bot;
        setAbilityBar = new SetAbilityBar(bot);
        doesInventoryContainWeapons = new DoesInventoryContainWeapons(bot);

    }

    @Override
    public boolean validate() {
        int bcBar=bot.getMonster().getCombatBar();
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