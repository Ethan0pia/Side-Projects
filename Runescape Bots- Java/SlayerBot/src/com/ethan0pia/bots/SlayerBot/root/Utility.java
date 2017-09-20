package com.ethan0pia.bots.SlayerBot.root;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves.LootItem;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.hybrid.util.StopWatch;

public class Utility {

    private Area bankAreaBurth = new Area.Rectangular( new Coordinate(2885,3538,0), new Coordinate(2893, 3534, 0));
    private  Area bankAreaDung = new Area.Rectangular(new Coordinate(3448,3719,0),new Coordinate(3451,3715,0));
    private Area bankAreaVarr = new Area.Circular(new Coordinate(3186,3440,0),8);
    private Area camelotBank = new Area.Rectangular(new Coordinate(2721,3493, 0),new Coordinate(2729,3490,0));
    private OpiaSlayer bot;
    private String stuckCheck="start", failTask;
    private StopWatch stuckWatch = new StopWatch();
    private int fails =0;
    private boolean turnBool;

    public Utility(OpiaSlayer bot){
        this.bot=bot;
    }

    //checks if player is at a mapped bank location
    public boolean amIatBank(){
        return bot.getPlayer() !=null && bankAreaBurth.contains(bot.getPlayer()) || bankAreaDung.contains(bot.getPlayer()) || bankAreaVarr.contains(bot.getPlayer()) || camelotBank.contains(bot.getPlayer());
    }

    //checks the ground for items worth picking up
    public GroundItem checkGround(){
        return GroundItems.newQuery().within(new Area.Circular(bot.getPlayer().getPosition(), 8)).filter(
                i -> i != null && i.getId()!=243 &&(((i.getDefinition().isTradeable() && checkPriceUnnoted(i) > bot.getMinVal()) || (i.getDefinition().isTradeable() && i.getDefinition().stacks() && checkPriceUnnoted(i) > bot.getMinValStack())
                        || (i.getDefinition().isNoted() && checkPriceNoted(i) > bot.getMinValStack()) || (i.getId() == 12158 && bot.getInfoUI().isLootGoldCharms()) || (i.getId() == 12159 && bot.getInfoUI().isLootGreenCharms())
                        || (i.getId() == 12160 && bot.getInfoUI().isLootCrimsonCharms()) || (i.getId() == 12163) && bot.getInfoUI().isLootBlueCharms()) || (i.getId() == 995 && i.getQuantity() > 750)) && RegionPath.buildTo(i)!=null).results().first();

    }

    //helper for checkGround
    private int checkPriceUnnoted(GroundItem item){
        int price = 0;
        if(item!=null) {
            int itemId = item.getId();
            if (bot.getStuff(itemId)) {
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
            if (bot.getStuff(itemId)) {
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
        if(bot.getPlayer()!=null && item !=null) {
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
            case "Leaf-bladed spear":
                if(Bank.contains("Leaf-bladed sword") && Bank.contains("Off-hand leaf-bladed sword")){

                }
                break;
            case "nose peg":
            case "Earmuffs":
            case "Spiny helmet":
            case "Face mask":
                if(Bank.contains("Slayer helmet")||Bank.contains("Full slayer helmet")||Bank.contains("Reinforced slayer helmet")
                   ||Bank.contains("Strong slayer helmet")||Bank.contains("Mighty slayer helmet")||Bank.contains("Corrupted slayer helmet")){

                }
        }
        return false;
    }


    //determines if the player is stuck and attempts to fix the issue. If it gets stuck again on the same slayer task, it will stop.
    //this makes it so the bot does not remain logged in for hours while doing nothing.
    public void stuckCheck(String leaf){

        if(stuckCheck.equals("start")){
            stuckWatch.start();
        }

        if(stuckCheck.equals(leaf)){
            if(!stuckWatch.isRunning()){
                stuckWatch.start();
            }
            if(stuckWatch.getRuntime()>100000 && turnBool){
                Camera.concurrentlyTurnTo(0.666);
                turnBool=false;
            }
            if(stuckWatch.getRuntime()>600000){
                if(fails>0 && failTask.equals(bot.getMonster().getMobName())){
                    Environment.getLogger().info("Bot failed in "+leaf);
                    bot.stop();
                }else{
                    if(Inventory.contains(bot.getqTeleport())){
                        SpriteItem teleport = Inventory.newQuery().ids(bot.getqTeleport()).results().first();
                        if(teleport.click()){
                            fails++;
                            failTask=bot.getMonster().getMobName();
                            turnBool=true;
                        }
                    }else{
                        Environment.getLogger().info("Bot failed in "+leaf);
                        bot.stop();
                    }
                }
            }
        }else{
            stuckCheck=leaf;
            stuckWatch.reset();
            if(!stuckWatch.isRunning()){
                stuckWatch.start();
            }
            turnBool=true;
        }
    }
}
