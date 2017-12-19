package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WalkLumbBank extends LeafTask {

    private OsrsOrbMaker bot;
    private Area groundFloor = new Area.Circular(new Coordinate(3214,3216,0),30);
    private Area topFloor = new Area.Circular(new Coordinate(3214,3216,2),30);
    private Area stairs = new Area.Circular(new Coordinate(3207,3209,0),4);

    public WalkLumbBank(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("WalkLumbBank");
        Player player = bot.getPlayer();
        if(groundFloor.contains(player) && !stairs.contains(player)){
            walkHere(new Coordinate(3207,3209,0));
        }else if(topFloor.contains(player)){
            walkHere(new Coordinate(3208,3219,2));
        }else{
            GameObject staircase = GameObjects.newQuery().names("Staircase").actions("Climb-up").results().nearest();
            if(staircase!=null){
                staircase.interact("Climb-up");
            }
        }

    }

    private void walkHere(Coordinate spot){
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(spot);
        if(path!=null){
            path.step();
        }
    }
}
