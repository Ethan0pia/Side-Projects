package com.ethan0pia.bots.SlayerBot.branches;

import com.ethan0pia.bots.SlayerBot.GoodAssSlayerBot;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES: done
 * Main AtBank - Checks if at bank before going to mob.
 */
public class AtBank extends BranchTask {

    private GoodAssSlayerBot Bot;

    public AtBank(GoodAssSlayerBot bot){
        Bot=bot;
        isbankopennospecialreq = new IsBankOpenNoSpecialReq(bot);
        atmonster = new AtMonster(bot);
    }

    private IsBankOpenNoSpecialReq isbankopennospecialreq;
    private AtMonster atmonster;

    private Area bankArea = new Area.Rectangular( new Coordinate(2885,3538,0), new Coordinate(2893, 3534, 0));

    @Override
    public boolean validate() {

        return Bot.player !=null && bankArea.contains(Bot.player) || new Area.Circular(new Coordinate(3449, 3717, 0), 40).contains(Bot.player);
    }

    @Override
    public TreeTask failureTask() {
        return atmonster;
    }

    @Override
    public TreeTask successTask() {
        return isbankopennospecialreq;
    }
}
