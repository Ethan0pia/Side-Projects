package com.ethan0pia.bots.slayer.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class bankOpenBranch extends BranchTask {
		//checks if bank is open
		private openBankBranch openBank = new openBankBranch();
        private specialGearCheckBranch gear = new specialGearCheckBranch();

        @Override
        public TreeTask successTask() {
            return gear;
        }

        @Override
        public TreeTask failureTask() {
            return openBank;
        }

        @Override
        public boolean validate() {
			//is bank open?
            return true;
        }


}