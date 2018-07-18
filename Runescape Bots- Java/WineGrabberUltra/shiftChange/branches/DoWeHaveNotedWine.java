package com.ethan0pia.bots.WineGrabberUltra.shiftChange.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.grabber.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class DoWeHaveNotedWine extends BranchTask {

    private AreWeAtEdgeBank areWeAtEdgeBank;
    private AreWeKillingBot areWeKillingBot;
    private EmptyLeaf emptyLeaf;
    private OpiaWineGrabberUltra bot;

    public DoWeHaveNotedWine(OpiaWineGrabberUltra bot){
        this.bot=bot;
        areWeAtEdgeBank = new AreWeAtEdgeBank(bot);
        areWeKillingBot = new AreWeKillingBot(bot);
        emptyLeaf = new EmptyLeaf(bot);
    }

    @Override
    public boolean validate() {
        return Inventory.contains(246);
    }

    @Override
    public TreeTask failureTask() {
        return areWeAtEdgeBank;
    }

    @Override
    public TreeTask successTask() {
        if(Bank.isOpen()){
            Bank.close();
            return emptyLeaf;
        }
        return areWeKillingBot;
    }
}
