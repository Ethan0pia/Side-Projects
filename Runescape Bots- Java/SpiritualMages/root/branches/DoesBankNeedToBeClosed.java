package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Do we have food, special item, runes/arrows, and can combat stuff be used.
 */
public class DoesBankNeedToBeClosed extends BranchTask {

    private IsBankOpenNotGeared isbankopennotgeared;
    private AmIInCombatNeedBank amIInCombatNeedBank;
    private OpiaSpiritualMages bot;
    private Area bank=new Area.Circular(new Coordinate(2725,3492,0),8);

    public DoesBankNeedToBeClosed(OpiaSpiritualMages bot){
        this.bot=bot;
        isbankopennotgeared = new IsBankOpenNotGeared(bot);
        amIInCombatNeedBank = new AmIInCombatNeedBank(bot);
    }

    @Override
    public boolean validate() {
        return bank.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amIInCombatNeedBank;
    }

    @Override
    public TreeTask successTask() {
        return isbankopennotgeared;
    }
}
