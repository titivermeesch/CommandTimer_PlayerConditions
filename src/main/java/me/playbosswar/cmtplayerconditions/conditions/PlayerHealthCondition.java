package me.playbosswar.cmtplayerconditions.conditions;

import me.playbosswar.com.conditionsengine.ConditionCompare;
import me.playbosswar.com.conditionsengine.conditions.ConditionHelpers;
import org.bukkit.entity.Player;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "HEALTH", description = "Check player health")
public class PlayerHealthCondition {
    @Condition
    public boolean execute(@Fact("player") Player p,
                           @Fact("conditionCompare") ConditionCompare conditionCompare,
                           @Fact("numericValue") double numericValue) {
        return ConditionHelpers.calculateConditionCompare(conditionCompare, p.getHealth(), numericValue);
    }

    @Action
    public void action() {}
}
