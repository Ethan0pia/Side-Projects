package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeInGWD extends BranchTask {

    private HaveWePassedBoulder ispathtocaveentrancenull;
    private AreWeNearObject isthereaspecificgameobjectnearby;
    OpiaSpiritualMages bot;
    private Area GWD=new Area.Circular(new Coordinate(2881,5312,0),150);

    public AreWeInGWD(OpiaSpiritualMages bot){
        this.bot=bot;
        ispathtocaveentrancenull = new HaveWePassedBoulder(bot);
        isthereaspecificgameobjectnearby = new AreWeNearObject(bot,"Broken bridge","Jump-over",new Coordinate(2887, 5336, 0));
    }

    @Override
    public boolean validate() {
        return GWD.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return ispathtocaveentrancenull;
    }

    @Override
    public TreeTask successTask() {
        if(Inventory.contains(3030)){
            SpriteItem potion = Inventory.newQuery().ids(3030).results().first();
            if(potion!=null){
                potion.click();
            }
        }
        return isthereaspecificgameobjectnearby;
    }
}
