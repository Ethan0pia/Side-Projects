package com.ethan0pia.bots.QBDBot.branches;

import com.ethan0pia.bots.QBDBot.OpiaQBD;
import com.ethan0pia.bots.QBDBot.leaves.SetBankBool;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class AreWeGeared extends BranchTask {

    private IsPhaseOne isphaseone;
    private SetBankBool setbankbool;
    private OpiaQBD bot;

    public AreWeGeared(OpiaQBD bot){
        this.bot = bot;
        isphaseone = new IsPhaseOne(bot);
        setbankbool = new SetBankBool(bot);
    }

    @Override
    public boolean validate() {
        boolean tp = !Inventory.newQuery().names("Ectophial", "Varrock teleport", "Lumbridge teleport","Falador teleport","Camelot teleport","Ardougne teleport","Teleport to house").results().isEmpty();
        boolean food = !Inventory.newQuery().actions("Eat").results().isEmpty();
        boolean cbType = Varbits.load(7618).getValue()==1 || Varbits.load(7618).getValue()==8;
        boolean antifire = !Inventory.newQuery().ids(15304,15305,15306,15307,23489,23490,23491,23492,23493,23494,2452,2454,2456,2458,23363,23365,23367,23369,23371,23373).results().isEmpty();
        boolean prayerPot = !Inventory.newQuery().ids(139, 141,143,2434,23243,23245,23247,23249,23251,23253,3024,3026,3028,3030,23399,23401,23403,23405,23407,23409).results().isEmpty();
        return tp && food && cbType && antifire && prayerPot;
    }

    @Override
    public TreeTask failureTask() {
        return setbankbool;
    }

    @Override
    public TreeTask successTask() {
        return isphaseone;
    }
}
