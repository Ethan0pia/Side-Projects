package com.ethan0pia.bots.GargoyleBot.branches;

import com.ethan0pia.bots.GargoyleBot.GargSlayer;
import com.ethan0pia.bots.GargoyleBot.leaves.AlchItem;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * 
 */
public class DoWeHaveAnItemToAlch extends BranchTask {

    private AlchItem alchitem;
    private AmIInCombat amiincombat;

    private GargSlayer bot;

    public DoWeHaveAnItemToAlch(GargSlayer bot){
        this.bot=bot;
        alchitem = new AlchItem(bot);
        amiincombat = new AmIInCombat(bot);
    }

    @Override
    public boolean validate() {
        if(bot.isToAlch()) {
            SpriteItem item = bot.getItemToAlch();
            SpriteItem fireRunes = Inventory.getItems("Fire rune").first();
            if(item!=null && item.isValid() && Inventory.contains(item.getId()) && Inventory.contains("Fire rune") && fireRunes != null && fireRunes.getQuantity() >= 5 && Inventory.contains("Nature rune") && Skill.MAGIC.getCurrentLevel() >= 55) {
                ActionBar.Slot ability = ActionBar.newQuery().names("High Level Alchemy").results().first();
                alchitem.setSlot(ability);
                return ability != null;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return amiincombat;
    }

    @Override
    public TreeTask successTask() {
        return alchitem;
    }
}
