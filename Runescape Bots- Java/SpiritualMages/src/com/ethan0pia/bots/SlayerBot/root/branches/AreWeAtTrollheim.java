package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeAtTrollheim extends BranchTask {

    private CanITrollheimTeleport canITrollheimTeleport;
    private IsThereASpecificGameObjectNearby isthereaspecificgameobjectnearby;
    private OpiaSpiritualMages bot;
    private Area trollheim=new Area.Circular(new Coordinate(2889,3671,0),80);

    public AreWeAtTrollheim(OpiaSpiritualMages bot){
        this.bot=bot;
        canITrollheimTeleport = new CanITrollheimTeleport(bot);
        isthereaspecificgameobjectnearby = new IsThereASpecificGameObjectNearby(bot,"Boulder","Lift",new Coordinate(2908, 3708, 0));
    }

    @Override
    public boolean validate() {
        return trollheim.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return canITrollheimTeleport;
    }

    @Override
    public TreeTask successTask() {
        return isthereaspecificgameobjectnearby;
    }
}
