package com.ethan0pia.bots.WineGrabberUltra.shiftChange.leaves;

import com.ethan0pia.bots.WineGrabberUltra.OpiaWineGrabberUltra;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WithdrawEverythingBank extends LeafTask {

    private OpiaWineGrabberUltra bot;

    public WithdrawEverythingBank(OpiaWineGrabberUltra bot){
        this.bot=bot;
    }


    @Override
    public void execute() {
        try {
            Environment.getLogger().debug("WithdrawEverythingBank");
            if(bot.isKill()){
                bot.setCurrentTask("Withdrawing wine and cursed essence");
                if (Inventory.containsAnyExcept(246, 37941)) {
                    if (Bank.depositInventory()) {
                        Execution.delayUntil(Inventory::isEmpty, 1000, 2000);
                    }
                } else if(!Equipment.isEmpty()){
                    if (Bank.depositEquipment()) {
                        Execution.delayUntil(Equipment::isEmpty, 1000, 2000);
                    }
                } else {
                    if (Bank.getWithdrawMode().equals(Bank.WithdrawMode.NOTE) || Bank.setWithdrawMode(Bank.WithdrawMode.NOTE)) {
                        if (Bank.contains(37941) && !Inventory.contains(37941)) {
                            Bank.withdraw(37941, 1);
                        } else if (Bank.contains(245)) {
                            Bank.withdraw(245, 200000);
                        } else if(!Inventory.contains(246)){
                            ClientUI.showAlert("No wine found in bank or inventory");
                            bot.setKill(false);
                        }
                    }
                }
            }else {
                bot.setCurrentTask("Withdrawing everything from the bank.");
                if (Inventory.containsAnyOf(245, 329)) {
                    if (Bank.depositInventory()) {
                        Execution.delayUntil(Inventory::isEmpty, 1000, 2000);
                    }
                } else {
                    if (Bank.getWithdrawMode().equals(Bank.WithdrawMode.NOTE) || Bank.setWithdrawMode(Bank.WithdrawMode.NOTE)) {
                        if (Bank.contains(563)) {
                            Bank.withdraw(563, 200000);
                        } else if (Bank.contains(329)) {
                            Bank.withdraw(329, 200000);
                        } else if (Bank.contains(37941)) {
                            Bank.withdraw(37941, 1);
                        } else if (Bank.contains(245)) {
                            Bank.withdraw(245, 200000);
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
