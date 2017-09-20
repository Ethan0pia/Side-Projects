package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class UseQuickTeleport extends LeafTask {

    private Area camelot = new Area.Circular(new Coordinate(2737,3483,0), 80);
    private OpiaSlayer bot;
    public UseQuickTeleport(OpiaSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        SpriteItem tablet = Inventory.getItems(bot.getqTeleport()).first();
        if (tablet!=null) {
            tablet.interact("Break");
        }
        Execution.delayUntil(() -> camelot.contains(bot.getPlayer()) || !Inventory.contains(bot.getqTeleport()), 1000,1500);
        bot.getUtils().stuckCheck("ao");
    }
}
