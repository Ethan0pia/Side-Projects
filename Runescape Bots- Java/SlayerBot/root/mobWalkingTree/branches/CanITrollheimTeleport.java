package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.CastTrollheimTeleport;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class CanITrollheimTeleport extends BranchTask {

    private AmIAtBankGWD amiatbankgwd;
    private CastTrollheimTeleport castTrollheimTeleport;
    OpiaSlayer bot;

    public CanITrollheimTeleport(OpiaSlayer bot){
        this.bot=bot;
        amiatbankgwd = new AmIAtBankGWD(bot);
        castTrollheimTeleport = new CastTrollheimTeleport(bot);

    }

    @Override
    public boolean validate() {
        return Inventory.contains(554) && Inventory.getItems(554).first().getQuantity()>=2 && Inventory.contains(563)&& Inventory.getItems(563).first().getQuantity()>=2;
    }

    @Override
    public TreeTask failureTask() {
        return amiatbankgwd;
    }

    @Override
    public TreeTask successTask() {
        return castTrollheimTeleport;
    }
}
