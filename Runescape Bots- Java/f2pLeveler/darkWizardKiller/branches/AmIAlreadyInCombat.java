package com.ethan0pia.bots.f2pLeveler.darkWizardKiller.branches;

import com.ethan0pia.bots.f2pLeveler.EmptyLeaf;
import com.ethan0pia.bots.f2pLeveler.darkWizardKiller.leaves.AttackWizard;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * 
 */
public class AmIAlreadyInCombat extends BranchTask {

    private EmptyLeaf emptyleaf = new EmptyLeaf();
    private AttackWizard attackwizard = new AttackWizard();

    @Override
    public boolean validate() {
        return Players.getLocal()!=null && Players.getLocal().getAnimationId()==-1 && Players.getLocal().getTarget()==null;
    }

    @Override
    public TreeTask failureTask() {
        return emptyleaf;
    }

    @Override
    public TreeTask successTask() {
        return attackwizard;
    }
}
