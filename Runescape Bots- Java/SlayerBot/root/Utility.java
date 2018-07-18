package com.ethan0pia.bots.SlayerBot.root;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves.LootItem;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;

public class Utility {

    private Area banks[]={new Area.Circular(new Coordinate(3511,3480,0),8),new Area.Circular(new Coordinate(2382,4458,0),8),new Area.Circular(new Coordinate(3093,3491,0),8),
            new Area.Circular(new Coordinate(2889,3536,0),8),new Area.Circular(new Coordinate(2725,3492,0),8),new Area.Circular(new Coordinate(2612,3092,0),8)
            ,new Area.Circular(new Coordinate(2876,3417,0),8),new Area.Circular(new Coordinate(3270,3167,0),8),new Area.Circular(new Coordinate(3306,3120,0),8)
            ,new Area.Circular(new Coordinate(2403,2841,0),8)};

    private OpiaSlayer bot;
    private int stuckCheck=0;
    private StopWatch stuckWatch = new StopWatch();
    private int fails =0,failTask;

    public Utility(OpiaSlayer bot){
        this.bot=bot;
    }

    //checks if player is at a mapped bank location
    public boolean amIatBank(){
        Player player=bot.getPlayer();
        for (Area i : banks) {
            if (i.contains(player)){
                return true;
            }
        }
        return false;
    }

    //checks the ground for items worth picking up
    public GroundItem checkGround(){
        return GroundItems.newQuery().within(new Area.Circular(bot.getPlayer().getPosition(), 10)).filter(
                i -> i != null && i.getId()!=243 &&(((i.getDefinition().isTradeable() && checkPriceUnnoted(i) > bot.getMinVal()) || (i.getDefinition().isTradeable() && i.getDefinition().stacks() && checkPriceUnnoted(i) > bot.getMinValStack())
                        || (i.getDefinition().isNoted() && checkPriceNoted(i) > bot.getMinValStack()) || (i.getId() == 12158 && bot.getInfoUI().isLootGoldCharms()) || (i.getId() == 12159 && bot.getInfoUI().isLootGreenCharms())
                        || (i.getId() == 12160 && bot.getInfoUI().isLootCrimsonCharms()) || (i.getId() == 12163) && bot.getInfoUI().isLootBlueCharms()) || (i.getId() == 995 && i.getQuantity() > 750)) && RegionPath.buildTo(i)!=null).results().first();

    }

    //helper for checkGround
    private int checkPriceUnnoted(GroundItem item){
        int price = 0;
        if(item!=null) {
            int itemId = item.getId();
            if (bot.getPriceMap(itemId)) {
                return bot.getValue(itemId);
            } else {
                GrandExchange.Item geItem = GrandExchange.lookup(itemId);
                if (geItem != null) {
                    price = geItem.getPrice();
                    bot.put(itemId, price);
                    int notedId = item.getDefinition().getNotedId();
                    bot.put(notedId, price);
                }
            }
        }
        return price;
    }

    //helper for checkGround
    private int checkPriceNoted(GroundItem item){
        int price = 0;
        if(item!=null) {
            int itemId = item.getId();
            if (bot.getPriceMap(itemId)) {
                return bot.getValue(itemId);
            } else {
                if (item.getDefinition() != null) {
                    ItemDefinition definition = item.getDefinition();
                    if (definition != null) {
                        int id = definition.getUnnotedId();
                        GrandExchange.Item geItem = GrandExchange.lookup(id);
                        if (geItem != null) {
                            price = geItem.getPrice();
                            bot.put(itemId, price);
                            bot.put(id, price);
                        }
                    }
                }
            }
        }
        return price;
    }

    //checks if the inventory has room for the item or not.
    public boolean inventoryHaveRoom(GroundItem item, LootItem lootitem){
        if(item !=null) {
            if(!Inventory.isFull()){
                lootitem.setItem(item);
                return false;
            }
            if (item.getDefinition().stacks() || item.getDefinition().isNoted()) {
                if (Inventory.contains(item.getId())) {
                    lootitem.setItem(item);
                    return false;
                }
            }
        }
        return true;
    }

    //checks to see if torch in inventory is lit
    public void checkTorch(){
        if (Inventory.contains("Unlit torch")) {
            Inventory.getItems("Unlit torch").first().interact("Light");
        }
    }


    //checks for alternative special items needed for various slayer monsters
    public boolean alternativeSpecials(){
        switch(bot.getMonster().getSpecialItem()){
            case 4168:
            case 4166:
            case 4551:
            case 4164:
                if(Equipment.contains(13263)||Equipment.contains(15492)||Equipment.contains(30656)
                   ||Equipment.contains(30686)||Equipment.contains(30716)||Equipment.contains("Corrupted slayer helmet")){
                   return true;
                }
        }
        return false;
    }

    public int alternativeSpecialsInBank(){
        switch(bot.getMonster().getSpecialItem()){
            case 4168:
            case 4166:
            case 4551:
            case 4164:
                if(Bank.contains(30716)){
                    return 30716;
                }else if(Bank.contains(30686)){
                    return 30686;
                }else if(Bank.contains(30656)){
                    return 30656;
                }else if(Bank.contains(15492)){
                    return 15492;
                }else if(Bank.contains(13263)){
                    return 13263;
                }
        }
        return 0;
    }


    //determines if the player is stuck and attempts to fix the issue. If it gets stuck again on the same slayer task, it will stop.
    //this makes it so the bot does not remain logged in for hours while doing nothing.
    public void stuckCheck(int leaf){

        if(stuckCheck==0){
            stuckWatch.start();
        }
        if(stuckCheck==leaf){
            if(!stuckWatch.isRunning()){
                stuckWatch.start();
            }
            if(stuckWatch.getRuntime()>100000 && stuckWatch.getRuntime()<110000){
                Camera.turnTo(180,Random.nextDouble(0.6,0.9));
            }
            if(stuckWatch.getRuntime()>600000) {
                if (Inventory.contains(bot.getqTeleport())) {
                    SpriteItem teleport = Inventory.newQuery().ids(bot.getqTeleport()).results().first();
                    teleport.click();
                }
                Environment.getLogger().debug("Bot failed in " + leaf);
                Environment.getLogger().severe("The bot got stuck on a task for more than 10 minutes.");
                bot.stop("Got stuck");
            }
        }else{
            stuckCheck=leaf;
            stuckWatch.reset();
            if(!stuckWatch.isRunning()){
                stuckWatch.start();
            }
        }
    }

    public Area closestBank(){
        double cost=0;
        Area closest = null;
        for(Area i:banks){
            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(i.getCenter());
            if(path!=null){
                double pathCost = path.getTraversalCost();
                WebPath pathFromBankToMob = Traversal.getDefaultWeb().getPathBuilder().build(i.getCenter(),bot.getMonster().getFinalWebCoord());
                if(pathFromBankToMob!=null){
                    pathCost=+pathFromBankToMob.getTraversalCost();
                }
                if(closest==null||pathCost<cost){
                    closest=i;
                    cost=pathCost;
                }
            }
        }
        return closest;
    }

    public void walkPath(Coordinate finalCoords){
        RegionPath regionPath = RegionPath.buildTo(finalCoords);
        if(regionPath!=null){
            if (regionPath.step()) {
                Execution.delayUntil(() -> !bot.getPlayer().isMoving(), 1000, 3000);
            } else {
                Camera.concurrentlyTurnTo(regionPath.getNext(), Random.nextDouble(0.6,0.9));
            }
        }else {
            WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(finalCoords);
            if (webPath != null) {
                if (webPath.step()) {
                    Execution.delayUntil(() -> !bot.getPlayer().isMoving(), 1000, 3000);
                } else {
                    Camera.concurrentlyTurnTo(webPath.getNext(), Random.nextDouble(0.6,0.9));
                }
            }
        }
    }
}
