package com.ethan0pia.bots.SlayerBot.root.gearedTree.branches;

import com.ethan0pia.bots.SlayerBot.OpiaSlayer;
import com.ethan0pia.bots.SlayerBot.root.gearedTree.leaves.AlchItem;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldWeAlch extends BranchTask {

    private OpiaSlayer bot;
    private AreItemsOnGroundWorthX areItemsOnGroundWorthX;
    private AlchItem alchItem;

    public ShouldWeAlch(OpiaSlayer bot){
        this.bot=bot;
        areItemsOnGroundWorthX=new AreItemsOnGroundWorthX(bot);
        alchItem= new AlchItem(bot);
    }

    @Override
    public boolean validate() {
        if(bot.isAlch() && bot.isToAlch()) {
            SpriteItem item = bot.getItemToAlch();
            SpriteItem fireRunes = Inventory.getItems("Fire rune").first();
            if(item!=null && item.isValid() && Inventory.contains(item.getId()) && Inventory.contains("Fire rune") && fireRunes != null && fireRunes.getQuantity() >= 5 && Inventory.contains("Nature rune") && Skill.MAGIC.getCurrentLevel() >= 55) {
                ActionBar.Slot ability = ActionBar.newQuery().names("High Level Alchemy").results().first();
                alchItem.setSlot(ability);
                return ability != null;
            }
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return areItemsOnGroundWorthX;
    }

    @Override
    public TreeTask successTask() {
        return alchItem;
    }
}