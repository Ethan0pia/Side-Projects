package com.ethan0pia.bots.GargoyleBot.leaves;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkBank extends LeafTask {

    private GargSlayer bot;
    private Area bankArea = new Area.Rectangular(new Coordinate(3512,3480,0), new Coordinate(3510,3478,0));

    public WalkBank(GargSlayer bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bankArea.getRandomCoordinate());
        if(path!=null){
            path.step();
        }
    }
}
