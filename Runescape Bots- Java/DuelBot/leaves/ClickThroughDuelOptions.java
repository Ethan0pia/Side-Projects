package com.ethan0pia.bots.DuelSlave.leaves;

import com.ethan0pia.bots.DuelSlave.DuelingSlave;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class ClickThroughDuelOptions extends LeafTask {

    private DuelingSlave bot;

    public ClickThroughDuelOptions(DuelingSlave bot){
        this.bot=bot;
    }

    @Override
    public void execute() {
        if(!bot.getDuelWatch().isRunning()){
            bot.getDuelWatch().stop();
            bot.getDuelWatch().reset();
        }
        try {
            InterfaceComponent firstScreen = Interfaces.newQuery().containers(1369).results().first();
            InterfaceComponent secondScreen = Interfaces.newQuery().containers(1367).results().first();
            InterfaceComponent thirdScreen = Interfaces.newQuery().containers(1366).results().first();
            InterfaceComponent fourthScreen = Interfaces.newQuery().containers(1365).results().first();

            if(fourthScreen!=null){
                InterfaceComponent rematch = Interfaces.newQuery().containers(1365).textContains("Offer rematch").results().last();
                if(rematch !=null) {
                    if (!bot.isMaster()) {
                        rematch.hover();
                        Execution.delay(100,200);
                        if(!rematch.interact("Offer rematch")){
                            rematch.click();
                        }
                    } else{
                        InterfaceComponent close = Interfaces.newQuery().containers(1365).textContains("Close").results().last();
                        if(close!=null){
                            if(!close.interact("Close")){
                                close.click();
                            }else{
                                Keyboard.typeKey(27);
                            }
                        }else {
                            Keyboard.typeKey(27);
                        }
                        Execution.delayUntil(()->Interfaces.newQuery().containers(1365).textContains("Offer rematch").results().isEmpty(),1000);
                    }
                }
            }
            if (thirdScreen != null && Interfaces.newQuery().containers(1366).textContains("Confirm").results().last() != null) {
                Interfaces.newQuery().containers(1366).textContains("Confirm").results().last().hover();
                InterfaceComponent confirm = Interfaces.newQuery().containers(1366).textContains("Confirm").results().last();
                if(confirm!=null) {
                    confirm.hover();
                    Execution.delay(100,200);
                    if (!confirm.interact("Confirm")) {
                        if(confirm.click()){
                            Execution.delayUntil(()->bot.getDuelArea().contains(bot.getPlayer()),1000, 1500);
                        }
                    }else{
                        Execution.delayUntil(()->bot.getDuelArea().contains(bot.getPlayer()),1000, 1500);
                    }
                }
            } else if (firstScreen != null) {
                InterfaceComponent bar = Interfaces.newQuery().containers(1369).actions("Select").results().first();
                if(bar!=null){
                    bar.hover();
                    Execution.delay(100,200);
                    if(!bar.interact("Select")) {
                        bar.click();
                    }
                }
                InterfaceComponent bar2 = Interfaces.newQuery().containers(1369).textContains("Send").results().first();
                if(bar2!=null) {
                    bar2.hover();
                    Execution.delay(100,200);
                    if(!bar2.interact("Send")) {
                        if(bar2.click()){
                            Execution.delayUntil(this::validateSecondInterface, 8000);
                        }
                    }else{
                        Execution.delayUntil(this::validateSecondInterface, 8000);
                    }
                }
                Execution.delayUntil(() -> secondScreen != null && Interfaces.newQuery().containers(1367).textContains("Accept").results() != null && Interfaces.newQuery().containers(1367).textContains("Accept").results().last().isVisible(), 600, 1000);
            } else if (secondScreen != null && Interfaces.newQuery().containers(1367).textContains("Accept").results() != null && Interfaces.newQuery().containers(1367).textContains("Accept").results().last().isVisible()) {
                InterfaceComponent accept = Interfaces.newQuery().containers(1367).textContains("Accept").results().last();
                if(accept!=null) {
                    accept.hover();
                    Execution.delay(100,200);
                    accept.interact("Accept");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private boolean validateSecondInterface(){
        try {
            //will return null if not correct.
            return Interfaces.newQuery().containers(1367).textContains("Accept").results().last().isVisible();
        }catch(Exception e) {
            return false;
        }
    }
}
