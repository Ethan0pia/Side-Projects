package com.ethan0pia.bots.slayer.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class slayerMasterVisibleBranch extends BranchTask {
    private String master;
    private slayerMasterVisibleBranch sMaster;
    private isGearedBranch geared;


    @Override
    public TreeTask successTask() {

        sMaster = new slayerMasterVisibleBranch()
        return sMaster;
    }

    @Override
    public TreeTask failureTask() {
        geared= new isGearedBranch();
        return sMaster;
    }

    @Override
    public boolean validate() {
        master="Turael";
        return true;
    }

}