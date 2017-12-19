package com.ethan0pia.bots.SlayerBot.root.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.CastTrollheimTeleport;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AreWeAtTrollheim extends BranchTask {

    private CastTrollheimTeleport castTrollheimTeleport;
    private AreWeNearObject isthereaspecificgameobjectnearby;
    private OpiaSpiritualMages bot;
    private Area trollheim=new Area.Circular(new Coordinate(2889,3671,0),80);

    public AreWeAtTrollheim(OpiaSpiritualMages bot){
        this.bot=bot;
        castTrollheimTeleport = new CastTrollheimTeleport(bot);
        isthereaspecificgameobjectnearby = new AreWeNearObject(bot,"Boulder","Lift",new Coordinate(2908, 3708, 0));
    }

    @Override
    public boolean validate() {
        return trollheim.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return castTrollheimTeleport;
    }

    @Override
    public TreeTask successTask() {
        return isthereaspecificgameobjectnearby;
    }
}
