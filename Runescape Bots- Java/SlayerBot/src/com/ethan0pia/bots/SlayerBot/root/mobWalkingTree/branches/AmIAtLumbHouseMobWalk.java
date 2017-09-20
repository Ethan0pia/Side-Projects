package com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.mobWalkingTree.leaves.EquipStaffandOpenDoor;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIAtLumbHouseMobWalk extends BranchTask {

    private EquipStaffandOpenDoor equipstaffandopendoor;
    private AmIInsideZanaris amiinsidezanaris;
    private Area zanarisDoor = new Area.Circular(new Coordinate(3199,3170,0),5);
    private OpiaSlayer bot;

    public AmIAtLumbHouseMobWalk(OpiaSlayer bot){
        this.bot=bot;
        equipstaffandopendoor = new EquipStaffandOpenDoor(bot);
        amiinsidezanaris = new AmIInsideZanaris(bot);
    }


    @Override
    public boolean validate() {
        return zanarisDoor.contains(bot.getPlayer());
    }

    @Override
    public TreeTask failureTask() {
        return amiinsidezanaris;
    }

    @Override
    public TreeTask successTask() {
        return equipstaffandopendoor;
    }
}
