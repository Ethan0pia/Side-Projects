package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.leaves.EquipWeapons;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class DoesInventoryContainWeapons extends BranchTask {

    private EquipWeapons equipWeapons;
    private AreSpellsSet areSpellsSet;
    private OpiaSlayer bot;

    public DoesInventoryContainWeapons(OpiaSlayer bot){
        this.bot=bot;
        equipWeapons = new EquipWeapons(bot);
        areSpellsSet = new AreSpellsSet(bot);

    }

    @Override
    public boolean validate() {
        String mainhand = bot.getMonster().getGear()[11];
        String offhand = bot.getMonster().getGear()[8];
        return (Inventory.contains(mainhand)&& !Equipment.contains(mainhand)) || (Inventory.contains(offhand)&& !Equipment.contains(offhand))|| (Equipment.contains(772) && !Inventory.isFull());
    }

    @Override
    public TreeTask failureTask() {
        return areSpellsSet;
    }

    @Override
    public TreeTask successTask() {
        return equipWeapons;
    }
}
