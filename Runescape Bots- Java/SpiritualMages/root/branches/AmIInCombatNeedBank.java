package com.ethan0pia.bots.SpiritualMages.root.branches;

import com.ethan0pia.bots.SpiritualMages.OpiaSpiritualMages;
import com.ethan0pia.bots.SpiritualMages.root.leaves.WalkBank;
import com.ethan0pia.bots.SpiritualMages.root.leaves.UseQuickTeleport;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AmIInCombatNeedBank extends BranchTask {

    private UseQuickTeleport useQuickTeleport;
    private WalkBank walkBank;
    private OpiaSpiritualMages bot;

    public AmIInCombatNeedBank(OpiaSpiritualMages bot){
        this.bot=bot;
        useQuickTeleport = new UseQuickTeleport(bot);
        walkBank = new WalkBank(bot);
    }

    @Override
    public boolean validate() {
        Npc attacking = Npcs.newQuery().targeting(bot.getPlayer()).actions("Attack").within(new Area.Circular(bot.getPlayer().getPosition(), 15)).filter(i->i.getTarget().equals(bot.getPlayer())).results().nearest();
        return attacking!=null && Inventory.contains(bot.getTeleport());
    }

    @Override
    public TreeTask failureTask() {
        return walkBank;
    }

    @Override
    public TreeTask successTask() {
        return useQuickTeleport;
    }
}