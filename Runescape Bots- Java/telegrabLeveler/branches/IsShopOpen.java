package com.ethan0pia.bots.TelegrabLeveler.branches;

import com.ethan0pia.bots.TelegrabLeveler.TelegrabLeveler;
import com.ethan0pia.bots.TelegrabLeveler.leaves.BuyBowAndEquip;
import com.ethan0pia.bots.TelegrabLeveler.leaves.TalkToShopOwner;
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
    private TelegrabLeveler bot;

    public IsShopOpen(TelegrabLeveler bot){
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
