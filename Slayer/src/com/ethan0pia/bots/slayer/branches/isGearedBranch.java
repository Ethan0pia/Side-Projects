package com.ethan0pia.bots.slayer.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class isGearedBranch extends BranchTask {
	//checks to see if player has the correct gear on to fight mobs.
	public boolean isGeared;
	//checks to see if user has at least 1 piece of food
	public boolean hasFood;




	    private atMonstersBranch monster = new atMonstersBranch();
        private atBankBranch geared = new atBankBranch();


        @Override
        public TreeTask successTask() {

            return monster;
        }

        @Override
        public TreeTask failureTask() {

            return geared;
        }

        @Override
        public boolean validate() {
            return hasFood&&isGeared;
        }

}