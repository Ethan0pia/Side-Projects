package com.ethan0pia.bots.OsrsOrbMaker.leaves;

import com.ethan0pia.bots.OsrsOrbMaker.OsrsOrbMaker;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class UseRoD extends LeafTask {

    private OsrsOrbMaker bot;
    private Area edgeville = new Area.Circular(new Coordinate(3093,3491,0),30);
    private Area clanWars = new Area.Circular(new Coordinate(3369,3170,0),35);

    public UseRoD(OsrsOrbMaker bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        System.out.println("UseRoD");
        SpriteItem rOd = Equipment.getItemIn(Equipment.Slot.RING);
        if (rOd != null) {
            if(rOd.interact("Clan Wars")){
                Execution.delayUntil(()->clanWars.contains(bot.getPlayer()),5000);
                if(edgeville.contains(bot.getPlayer())){
                    bot.setBeenInPortal(false);
                    bot.setBoughtGlories(false);
                    bot.setSoldGlories(false);
                    bot.setGetGlories(false);
                }
            }
        }else if(Bank.isOpen() && Bank.contains(2566) && Bank.withdraw(2566,1) || Inventory.contains(2566)) {
            Execution.delay(2000,3000);
            SpriteItem ring = Inventory.newQuery().ids(2566).results().first();
            if((!Bank.isOpen() || Bank.close()) && ring!=null){
                if(ring.click()){
                    Execution.delayUntil(()->Equipment.getItemIn(Equipment.Slot.RING)!=null,2000);
                }
            }
        }else{
            if(clanWars.contains(bot.getPlayer())){
                WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(clanWars.getCenter());
                if (path != null) {
                    Execution.delayUntil(path::step, 2000);
                }
            }else{
                WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(edgeville.getCenter());
                if (path != null) {
                    Execution.delayUntil(path::step, 2000);
                }
            }
        }
    }
}
