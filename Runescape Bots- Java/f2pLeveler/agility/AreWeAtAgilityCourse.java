package com.ethan0pia.bots.f2pLeveler.agility;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class AreWeAtAgilityCourse extends BranchTask {

    private RunCourse runcourse = new RunCourse();
    private WalkToCourse walktocourse = new WalkToCourse();
    private Area course = new Area.Rectangular(new Coordinate(2908,3566,0),new Coordinate(2922,3550,0));
    private Area course2 = new Area.Circular(new Coordinate(2910,3561,1), 40);

    @Override
    public boolean validate() {
        return course.contains(Players.getLocal())|| course2.contains(Players.getLocal());
    }

    @Override
    public TreeTask failureTask() {
        return walktocourse;
    }

    @Override
    public TreeTask successTask() {
        return runcourse;
    }
}
