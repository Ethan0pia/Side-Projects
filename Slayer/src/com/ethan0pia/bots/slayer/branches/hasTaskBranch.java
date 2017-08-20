package com.ethan0pia.bots.slayer.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.runemate.game.api.hybrid.local.Varbits;

//checks for a task
public class hasTaskBranch extends BranchTask {
        private slayerMasterVisibleBranch sMaster= new slayerMasterVisibleBranch();
        private isGearedBranch geared= new isGearedBranch();


        @Override
        public TreeTask successTask() {

            return sMaster;
        }

        @Override
        public TreeTask failureTask() {

            return geared;
        }

        @Override
        public boolean validate() {
            return Varbits.load(7917).getValue()==0;
        }



}