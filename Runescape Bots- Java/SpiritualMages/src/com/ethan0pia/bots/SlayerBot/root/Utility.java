package com.ethan0pia.bots.SlayerBot.root;

import com.ethan0pia.bots.SlayerBot.OpiaSpiritualMages;
import com.ethan0pia.bots.SlayerBot.root.leaves.LootItem;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.MoneyPouch;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;

public class Utility {

    private Area banks[]={new Area.Circular(new Coordinate(3511,3480,0),8),new Area.Circular(new Coordinate(2382,4458,0),8),new Area.Circular(new Coordinate(3093,3491,0),8),
            new Area.Circular(new Coordinate(2889,3536,0),8),new Area.Circular(new Coordinate(2725,3492,0),8),new Area.Circular(new Coordinate(2612,3092,0),8)
            ,new Area.Circular(new Coordinate(2876,3417,0),8),new Area.Circular(new Coordinate(3270,3167,0),8),new Area.Circular(new Coordinate(3306,3120,0),8)
            ,new Area.Circular(new Coordinate(2403,2841,0),8)};

    private OpiaSpiritualMages bot;
    private int stuckCheck=0;
    private StopWatch stuckWatch = new StopWatch();

    public Utility(OpiaSpiritualMages bot){
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
                Camera.turnTo(180,0.666);
            }
            if(stuckWatch.getRuntime()>600000) {
                if (Inventory.contains(8010)) {
                    SpriteItem teleport = Inventory.newQuery().ids(8010).results().first();
                    teleport.click();
                }
                Environment.getLogger().debug("Bot failed in " + leaf);
                Environment.getLogger().severe("The bot got stuck on a task for more than 10 minutes.");
                bot.stop("FAILED");
            }
        }else{
            stuckCheck=leaf;
            stuckWatch.reset();
            if(!stuckWatch.isRunning()){
                stuckWatch.start();
            }
        }
    }

    public void walkPath(Coordinate finalCoords){
        RegionPath regionPath = RegionPath.buildTo(finalCoords);
        if(regionPath!=null){
            if (regionPath.step()) {
                Execution.delayUntil(() -> !bot.getPlayer().isMoving(), 1000, 3000);
            } else {
                Camera.concurrentlyTurnTo(regionPath.getNext(), Random.nextDouble(0.6, 0.666));
            }
        }else {
            WebPath webPath = Traversal.getDefaultWeb().getPathBuilder().buildTo(finalCoords);
            if (webPath != null) {
                if (webPath.step()) {
                    Execution.delayUntil(() -> !bot.getPlayer().isMoving(), 1000, 3000);
                } else {
                    Camera.concurrentlyTurnTo(webPath.getNext(), Random.nextDouble(0.6, 0.666));
                }
            }
        }
    }

    private int alchCost = 0;

    public void shouldWeAlch() {
        if (bot.isAlch() && bot.isToAlch()) {
            SpriteItem item = bot.getItemToAlch();
            SpriteItem fireRunes = Inventory.getItems("Fire rune").first();
            if (item != null && item.isValid() && Inventory.contains(item.getId()) && Inventory.contains("Fire rune") && fireRunes != null && fireRunes.getQuantity() >= 5 && Inventory.contains("Nature rune") && Skill.MAGIC.getCurrentLevel() >= 55) {
                ActionBar.Slot ability = ActionBar.newQuery().names("High Level Alchemy").results().first();
                if(ability!=null){
                    if(alchCost==0){
                        int fireRunesCost = GrandExchange.lookup(554).getPrice();
                        int natureRuneCost = GrandExchange.lookup(561).getPrice();
                        alchCost = fireRunesCost*5+natureRuneCost;
                    }

                    if(!InterfaceWindows.getInventory().isOpen()){
                        InterfaceWindows.getInventory().open();
                        Execution.delayUntil(()->InterfaceWindows.getInventory().isOpen(),1200);
                    }
                    int coins = MoneyPouch.getContents();
                    int itemPrice = bot.getValue(bot.getItemToAlch().getId());

                    if(ability.getKeyBind()!=null) {
                        if(ability.activate(false)){
                            Execution.delay(250,350);
                            if(bot.getItemToAlch().click()){
                                Execution.delayUntil(()->MoneyPouch.getContents()>coins,1500);
                            }
                        }
                    }else{
                        if(!ability.activate(true)){
                            if(ability.activate(false)){
                                Execution.delay(250,350);
                                if(bot.getItemToAlch().click()){
                                    Execution.delayUntil(()->MoneyPouch.getContents()>coins,1500);
                                }
                            }
                        }else{
                            Execution.delay(250,350);
                            if(bot.getItemToAlch().click()){
                                Execution.delayUntil(()->MoneyPouch.getContents()>coins,1500);
                            }
                        }
                    }

                    int newCoinAmt = MoneyPouch.getContents();
                    if(newCoinAmt!=coins){
                        int gpGained = newCoinAmt-coins-alchCost-itemPrice;
                        bot.setGpGained(bot.getGpGained()+gpGained);
                    }
                    bot.getUtils().stuckCheck(14);
                }
            }
        }
    }
}
