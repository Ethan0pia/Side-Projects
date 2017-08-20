package com.ethan0pia.bots.slayer;
import com.ethan0pia.bots.CowKillerTanner.branches.atCows;


import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.runemate.game.api.rs3.local.hud.interfaces.Lodestone;//loadstones

//root
import com.ethan0pia.bots.CowKillerTanner.branches.atCows;

public class main extends TreeBot {

	@Override
	public TreeTask createRootTask() {

		return new atCows();
	}
}


//--------------------------------------------------Fighting--------------------------------------------------------------//
/* Example fighter
import com.runemate.game.api.hybrid.Environment
import com.runemate.game.api.hybrid.region.Npcs
import com.runemate.game.api.hybrid.region.Players
import com.runemate.game.api.script.Execution
import com.runemate.game.api.script.framework.tree.LeafTask
import java.util.concurrent.TimeUnit

 * @author Septron
 * @since June 05, 2017
 *
class AttackChickenLeaf : LeafTask() {

    override fun execute() {
        val results = Npcs.newQuery().names("Cow").actions("Attack").reachable().results()
        if (results.isNotEmpty()) {
            val npc = results.nearest()
            if (npc!!.interact("Attack")) {
                if (Execution.delayUntil({ !npc.isValid }, { Players.getLocal().isMoving },
                        500, TimeUnit.SECONDS.toMillis(10).toInt()
                )){
                    Environment.getLogger().info("We killed a Cow")
                } else {
                    Environment.getLogger().info("We failed to kill a Cow")
                }
            } else {
                Environment.getLogger().info("Failed to attack a Cow")
            }
        } else {
            Environment.getLogger().info("Failed to find a Cow")
        }
    }
}
*/