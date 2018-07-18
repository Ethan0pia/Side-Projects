package com.ethan0pia.bots.CowKiller.branches;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.rs3.local.hud.interfaces.LootInventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.ethan0pia.bots.CowKiller.leaves.lootHide;
import com.ethan0pia.bots.CowKiller.leaves.attackCow;

public class CheckGround extends BranchTask {

	private Area cowArea= new Area.Rectangular(new Coordinate(2881,3493,0), new Coordinate(2890,3481,0));

	private lootHide loot= new lootHide();
	private attackCow attack= new attackCow();

	@Override
	public TreeTask successTask() {
        return attack;
	}

	@Override
	public TreeTask failureTask() {
        return loot;
	}

	@Override
	public boolean validate() {
		//cowhide on ground?
		return GroundItems.newQuery().names("Cowhide").within(cowArea).results().isEmpty() && !LootInventory.isOpen();
	}

}