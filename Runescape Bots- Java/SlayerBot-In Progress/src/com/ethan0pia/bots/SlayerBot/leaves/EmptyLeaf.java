package com.ethan0pia.bots.SlayerBot.leaves;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EmptyLeaf extends LeafTask {

    @Override
    public void execute() {


    }
}
    /*
    private Player player;
    private Coordinate cowCoords;
    private final Area cowArea= new Area.Rectangular(new Coordinate(2026, 5238,0), new Coordinate(2034, 5231,0));

    @Override
    public void execute() {
        cowCoords = new Coordinate(2201, 4405,0);
        player = Players.getLocal();
        if (player!=null && cowCoords!=null) {
            final WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(cowCoords);
            System.out.println(path);
            if (path != null) {
                path.step(true);
            }
        }
    }
    */