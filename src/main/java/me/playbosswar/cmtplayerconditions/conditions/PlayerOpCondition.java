package me.playbosswar.cmtplayerconditions.conditions;

import org.bukkit.entity.Player;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "IS OP", description = "Check if player has op")
public class PlayerOpCondition {
    @Condition
    public boolean execute(@Fact("player") Player p) {
        return p.isOp();
    }

    @Action
    public void action() {}
}
