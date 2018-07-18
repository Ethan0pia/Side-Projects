package com.ethan0pia.bots.WineGrabberUltra.leveler.branches;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.ethan0pia.bots.WineGrabberUltra.leveler.leaves.BuyBowAndEquip;
import com.ethan0pia.bots.WineGrabberUltra.leveler.leaves.TalkToShopOwner;
import com.runemate.game.api.hybrid.local.hud.interfaces.Shop;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class IsShopOpen extends BranchTask {

    private BuyBowAndEquip buyswordandequip;
    private TalkToShopOwner talktoshopowner;
    private OpiaWineGrabberUltra bot;

    public IsShopOpen(OpiaWineGrabberUltra bot){
        this.bot=bot;
        talktoshopowner = new TalkToShopOwner(bot);
        buyswordandequip = new BuyBowAndEquip(bot);
    }


    @Override
    public boolean validate() {
        return Shop.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return talktoshopowner;
    }

    @Override
    public TreeTask successTask() {
        return buyswordandequip;
    }
}
