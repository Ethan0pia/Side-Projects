package com.ethan0pia.bots.SlayerBot.root.leaves;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class UseQuickTeleport extends LeafTask {

    private Area camelot = new Area.Circular(new Coordinate(2737,3483,0), 80);
    private OpiaSpiritualMages bot;

    public UseQuickTeleport(OpiaSpiritualMages bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        SpriteItem tablet = Inventory.getItems(8010).first();
        if (tablet!=null) {
            tablet.interact("Break");
        }
        Execution.delayUntil(() -> camelot.contains(bot.getPlayer()) || !Inventory.contains(8010), 1000);
        bot.getUtils().stuckCheck(42);
    }
}
