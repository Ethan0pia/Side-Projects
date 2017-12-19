package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.CloseBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Are we at the bank and geared?
 */
public class AmIAtBankGeared extends BranchTask {

    private CloseBank closeBank;
    private AmIAtMob amiatmob;
    private OpiaSpiritualMages bot;

    private Area bank=new Area.Circular(new Coordinate(2725,3492,0),8);

    public AmIAtBankGeared(OpiaSpiritualMages bot){
        this.bot=bot;
        closeBank = new CloseBank(bot);
        amiatmob = new AmIAtMob(bot);
    }


    @Override
    public boolean validate() {
        return bank.contains(bot.getPlayer()) && Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return amiatmob;
    }

    @Override
    public TreeTask successTask() {
        return closeBank;
    }
}
