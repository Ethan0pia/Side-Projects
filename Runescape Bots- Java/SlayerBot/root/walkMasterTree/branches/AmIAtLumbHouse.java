package com.ethan0pia.bots.SlayerBot.root.walkMasterTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.walkMasterTree.leaves.InteractGameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIAtLumbHouse extends BranchTask {

    private InteractGameObject interactgameobject;
    private AmIInZanaris amiinzanaris;
    private OpiaSlayer bot;
    private Area zanarisDoor = new Area.Circular(new Coordinate(3199,3170,0),5);

    public AmIAtLumbHouse(OpiaSlayer bot){
        this.bot=bot;
        interactgameobject = new InteractGameObject(bot,"Door","Open");
        amiinzanaris = new AmIInZanaris(bot);
    }


    @Override
    public boolean validate() {
        return zanarisDoor.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amiinzanaris;
    }

    @Override
    public TreeTask successTask() {
        return interactgameobject;
    }
}
