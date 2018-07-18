package com.ethan0pia.bots.WineGrabberUltra.leveler.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionWindow;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import static com.runemate.game.api.hybrid.input.Mouse.Button.LEFT;

/**
 * NOTES:
 * 
 */
public class EnterCave extends LeafTask {

    private OpiaWineGrabberUltra bot;
    public EnterCave(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            if(checkAbilities()) {
                Environment.getLogger().debug("EnterCave");
                bot.setCurrentTask("Entering the troll cave.");
                GameObject cave = GameObjects.newQuery().names("Cave entrance").results().nearest();
                if (cave != null) {
                    cave.interact("Enter");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private boolean checkAbilities(){
        InterfaceContainer lock;
        if(!bot.isChecked()){
            bot.setTelegrab(ActionBar.newQuery().names("Telekinetic Grab").results().first());
            bot.setFirstThreshold(ActionBar.newQuery().names("Asphyxiate").results().first());
            bot.setMagicBasic1(ActionBar.newQuery().names("Wrack").results().first());
            bot.setMagicBasic2(ActionBar.newQuery().names("Impact").results().first());
            bot.setMagicBasic3(ActionBar.newQuery().names("Dragon Breath").results().first());
            bot.setMagicBasic4(ActionBar.newQuery().names("Sonic Wave").results().first());
            bot.setRangedBasic(ActionBar.newQuery().names("Piercing Shot").results().first());
            bot.setFood(ActionBar.newQuery().names(bot.getFoodType()).results().first());
            bot.setChecked(true);
            return false;
        }else if((lock = InterfaceContainers.getAt(1430))!=null && lock.getComponent(259).getActions().get(0).equals("Unlock action bar")) {
            if(lock.getComponent(259).interact("Unlock action bar")) {
                Execution.delayUntil(()->!lock.getComponent(259).getActions().get(0).equals("Unlock action bar"),100,1000);
            }
            return false;
        }else if(bot.getTelegrab()==null) {
            if(InterfaceWindows.getMagic().isOpen() || InterfaceWindows.getMagic().open()){
                if(InterfaceWindows.getMagic().isOpen() || InterfaceWindows.getMagic().open()){
                    InterfaceContainer inter = InterfaceContainers.getAt(1461);
                    if(inter!=null) {
                        InterfaceComponent childItem = inter.getComponents().get(1).getChild(32);
                        if (childItem != null && childItem.isVisible()) {
                            if(Mouse.drag(childItem, InterfaceContainers.getAt(1430).getComponent(62))){
                                Execution.delayUntil(() -> ActionBar.newQuery().names("Telekinetic Grab").results().first() != null, 500, 1000);
                                bot.setTelegrab(ActionBar.newQuery().names("Telekinetic Grab").results().first());
                            }else if(Mouse.isPressed()){
                                Mouse.release(LEFT);
                            }
                        } else {
                            InterfaceComponent skillButton = Interfaces.newQuery().actions("Skilling").visible().results().first();
                            if (skillButton != null) {
                                skillButton.click();
                            }
                        }
                    }
                }
                bot.setTelegrab(ActionBar.newQuery().names("Telekinetic Grab").results().first());
            }
            return false;
        }else if(bot.getRangedBasic()==null){
            InterfaceContainer inter = InterfaceContainers.getAt(1452);
            if(inter!=null) {
                InterfaceComponent childItem = inter.getComponent(1).getChild(1);
                if (childItem != null && childItem.isVisible()) {
                    if(Mouse.drag(childItem, InterfaceContainers.getAt(1430).getComponent(75))){
                        Execution.delayUntil(() -> ActionBar.newQuery().names("Piercing Shot").results().first() != null, 500, 1000);
                    }else if(Mouse.isPressed()){
                        Mouse.release(LEFT);
                    }
                } else {
                    ActionWindow.RANGED_ABILITIES.open();
                }
            }
            bot.setRangedBasic(ActionBar.newQuery().names("Piercing Shot").results().first());
            return false;
        }else if(bot.getFirstThreshold()==null){
            bot.setFirstThreshold(setAbility("Asphyxiate", 7,88));
            return false;
        }else if(bot.getMagicBasic1()==null){
            bot.setMagicBasic1(setAbility("Wrack",1,101));
            return false;
        }else if(bot.getMagicBasic2()==null){
            bot.setMagicBasic2(setAbility("Impact",3,114));
            return false;
        }else if(bot.getMagicBasic3()==null){
            bot.setMagicBasic3(setAbility("Dragon Breath",6,127));
            return false;
        }else if(bot.getMagicBasic4()==null){
            bot.setMagicBasic4(setAbility("Sonic Wave",165,140));
            return false;
        }else if(bot.getTeleportAbility()==null) {
            if (InterfaceWindows.getMagic().isOpen() || InterfaceWindows.getMagic().open()) {
                if (InterfaceWindows.getMagic().isOpen() || InterfaceWindows.getMagic().open()) {
                    InterfaceContainer inter = InterfaceContainers.getAt(1461);
                    if (inter != null) {
                        InterfaceComponent childItem = inter.getComponents().get(1).getChild(34);
                        if (childItem != null && childItem.isVisible()) {
                            if(Mouse.drag(childItem, InterfaceContainers.getAt(1430).getComponent(153))) {
                                Execution.delayUntil(() -> ActionBar.newQuery().names("Falador Teleport").results().first() != null, 500, 1000);
                            }else if(Mouse.isPressed()){
                                Mouse.release(LEFT);
                            }
                        } else {
                            InterfaceComponent skillButton = Interfaces.newQuery().actions("Teleport").visible().results().first();
                            if (skillButton != null) {
                                skillButton.click();
                            }
                        }
                    }
                }
                bot.setTeleportAbility(ActionBar.newQuery().names("Falador Teleport").results().first());
            }
            return false;
        }
        return true;
    }


    private ActionBar.Slot setAbility(String ability, int child, int spot){
        Environment.getLogger().debug("SettingSkills");
        bot.setCurrentTask("Setting up skills.");
        if(InterfaceWindows.getMagic().isOpen() || InterfaceWindows.getMagic().open()){
            InterfaceContainer inter = InterfaceContainers.getAt(1461);
            if(inter!=null) {
                InterfaceComponent childItem = inter.getComponent(1).getChild(child);
                if (childItem != null && childItem.isVisible()) {
                    if(Mouse.drag(childItem, InterfaceContainers.getAt(1430).getComponent(spot))) {
                        Execution.delayUntil(() -> ActionBar.newQuery().names(ability).results().first() != null, 500, 1000);
                    }else if(Mouse.isPressed()){
                        Mouse.release(LEFT);
                    }
                } else {
                    InterfaceComponent tab = inter.getComponent(7).getChild(7);
                    if (tab != null && tab.isVisible()) {
                        tab.interact("Abilities");
                    }
                }
            }
        }
        return ActionBar.newQuery().names(ability).results().first();
    }
}
