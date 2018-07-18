package com.ethan0pia.bots.QBDBot.EatingThread.branches;

import com.ethan0pia.bots.QBDBot.EatingThread.leaves.DrinkStatPotion;
import com.ethan0pia.bots.QBDBot.EatingThread.leaves.EmptyLeaf;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class AreStatPotionsActive extends BranchTask {

    private EmptyLeaf emptyleaf = new EmptyLeaf();
    private DrinkStatPotion drinkstatpotion = new DrinkStatPotion();


    @Override
    public boolean validate() {
        Environment.getLogger().debug("EatingThread:AreStatPotionsActive");
        if(Varbits.load(500).getValue()==0){
            //overloads
            SpriteItem item = Inventory.newQuery().ids(15332, 15333,15334,15335,23531,23532,23533,23534,23535,23536).results().first();
            if(item!=null){
                drinkstatpotion.setPotion(1);
                return false;
            }
        }
        if(Varbits.load(497).getValue()==0 && Varbits.load(498).getValue()==0){
            //antifires
            SpriteItem item = Inventory.newQuery().ids(15304,15305,15306,15307,23489,23490,23491,23492,23493,23494,2452,2454,2456,2458,23363,23365,23367,23369,23371,23373).results().first();
            if(item!=null) {
                drinkstatpotion.setPotion(2);
                return false;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return drinkstatpotion;
    }

    @Override
    public TreeTask successTask() {
        return emptyleaf;
    }
}
