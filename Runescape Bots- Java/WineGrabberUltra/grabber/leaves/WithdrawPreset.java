package com.ethan0pia.bots.WineGrabberUltra.grabber.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.queries.InterfaceComponentQueryBuilder;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import static com.runemate.game.api.hybrid.input.Mouse.Button.LEFT;

public class WithdrawPreset extends LeafTask {

    private OpiaWineGrabberUltra bot;

    public WithdrawPreset(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WithdrawPreset");
            bot.setCurrentTask("Withdrawing preset.");
            if(!bot.isBankPreset() && !bot.isDisableBankPreset()){
                setPreset();
            }else {
                if (Bank.loadPreset(1)) {
                    Execution.delayUntil(() -> !Bank.isOpen(), 500, 2000);
                    if (!Bank.isOpen()) {
                        bot.setBankBool(false);
                        bot.setRun(false);
                        checkAbilities();
                        if (!Inventory.contains(bot.getFoodType())) {
                            ActionBar.Slot food = bot.getFood();
                            if (bot.getFood() == null || !food.isActivatable()) {
                                Environment.getLogger().debug("Out of Food!");
                                bot.setForceShiftEnd(true);
                                return;
                            }
                        } else if (!Inventory.contains(563)) {
                            ActionBar.Slot telegrabSpell = bot.getTelegrab();
                            if (telegrabSpell == null || !telegrabSpell.isActivatable()) {
                                Environment.getLogger().debug("Out of Runes!");
                                bot.setForceShiftEnd(true);
                                return;
                            }
                        }
                        if ((Camera.getYaw() < bot.getCameraYaw()-2 || Camera.getYaw() > bot.getCameraYaw()+2) || Camera.getPitch()<bot.getCameraPitch()) {
                            Camera.turnTo(bot.getCameraYaw(), bot.getCameraPitch(), 0.2);
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void equipArmor(int id){
        SpriteItem armor = Bank.newQuery().ids(id).results().first();
        if (armor != null) {
            Bank.equip(armor);
        }
    }

    private void setPreset(){
        bot.setCurrentTask("Setting up preset.");
        String foodType = bot.getFoodType();
        if(Inventory.containsAnyExcept(foodType,"Law rune","Water rune")){
            Bank.depositInventory();
        }
        if(!Equipment.contains(1381)){
            SpriteItem staff = Bank.newQuery().ids(1381).results().first();
            if(staff!=null) {
                Bank.equip(staff);
            }else{
                Environment.getLogger().severe("Staff could not be found for preset!");
                Environment.getBot().stop("Staff could not be found for preset!");
            }
        }else if(!Equipment.contains(25857) && Bank.contains(25857)){
            equipArmor(25857);
        } else if(!Equipment.contains(25859) && Bank.contains(25859)){
            equipArmor(25859);
        } else if(!Equipment.contains(25861) && Bank.contains(25861)){
            equipArmor(25861);
        }else if(!Equipment.contains(25863) && Bank.contains(25863)){
            equipArmor(25863);
        }else if(!Equipment.contains(25865) && Bank.contains(25865)){
            equipArmor(25865);
        }else if(!Inventory.contains(555) && Bank.contains(555)){
            Bank.withdraw(555, 2);
        }else if(!Inventory.contains(563)){
            if(Bank.contains(563)) {
                Bank.withdraw(563, 40);
            }else{
                Environment.getLogger().severe("Law runes could not be found for preset!");
                Environment.getBot().stop("Law runes could not be found for preset!");
            }
        }else if(!Inventory.contains(foodType)){
            if(Bank.contains(foodType)) {
                Bank.withdraw(foodType, 20);
            }else{
                Environment.getLogger().severe("Food could not be found for preset!");
                Environment.getBot().stop("Food could not be found for preset!");
            }
        }else{
            GameEvents.Universal.INTERFACE_CLOSER.disable();
            InterfaceContainer savePreset = InterfaceContainers.getAt(762);
            InterfaceComponent confirmButton;
            if (savePreset != null) {
                if ((confirmButton = Interfaces.newQuery().containers(762).visible().textContains("Confirm").results().first()) != null) {
                    if(confirmButton.click()) {
                        Execution.delayWhile(confirmButton::isVisible, 1000, 2000);
                        if (!confirmButton.isVisible()) {
                            GameEvents.Universal.INTERFACE_CLOSER.enable();
                            bot.setBankPreset(true);
                        }
                    }
                } else {
                    InterfaceComponent saveButton;
                    if ((saveButton = Interfaces.newQuery().containers(762).visible().actions("Save").results().first()) != null) {
                        saveButton.interact("Save");
                    } else {
                        InterfaceComponent managePresetButton;
                        if ((managePresetButton = Interfaces.newQuery().containers(762).visible().actions("Select").results().first()) != null) {
                            managePresetButton.click();
                        }
                    }
                }
            }
        }
    }

    private void checkAbilities(){
        if (bot.getFood() == null) {
            if (InterfaceWindows.getInventory().isOpen() || InterfaceWindows.getInventory().open()) {
                if (Mouse.drag(Inventory.getItems(bot.getFoodType()).first(), Interfaces.newQuery().containers(1430).actions("Customise-keybind").results().get(1))) {
                    Execution.delayUntil(() -> ActionBar.newQuery().names(bot.getFoodType()).results().first() != null, 500, 1000);
                    bot.setFood(ActionBar.newQuery().names(bot.getFoodType()).results().first());
                }else if(Mouse.isPressed()){
                        Mouse.release(LEFT);
                }
            }
        }

        if(bot.getTelegrab() == null) {
            if (InterfaceWindows.getMagic().isOpen() || InterfaceWindows.getMagic().open()) {
                if (InterfaceWindows.getMagic().isOpen() || InterfaceWindows.getMagic().open()) {
                    InterfaceContainer inter = InterfaceContainers.getAt(1461);
                    if (inter != null) {
                        InterfaceComponent childItem = inter.getComponents().get(1).getChild(32);
                        if (childItem != null && childItem.isVisible()) {
                            if(Mouse.drag(childItem, InterfaceContainers.getAt(1430).getComponent(62))) {
                                Execution.delayUntil(() -> ActionBar.newQuery().names("Telekinetic Grab").results().first() != null, 500, 1000);
                            } else if(Mouse.isPressed()){
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
            }
            bot.setTelegrab(ActionBar.newQuery().names("Telekinetic Grab").results().first());
        }
    }
}
