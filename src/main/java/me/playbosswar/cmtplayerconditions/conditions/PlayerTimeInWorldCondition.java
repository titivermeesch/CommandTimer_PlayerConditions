package me.playbosswar.cmtplayerconditions.conditions;

import me.playbosswar.cmtplayerconditions.utils.WorldTimeTracking;
import me.playbosswar.com.conditionsengine.ConditionCompare;
import me.playbosswar.com.conditionsengine.conditions.ConditionHelpers;
import org.bukkit.entity.Player;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "TIME_IN_WORLD", description = "Check for how long the player is in the same world")
public class PlayerTimeInWorldCondition {
    private final WorldTimeTracking worldTimeTracking;

    public PlayerTimeInWorldCondition(WorldTimeTracking worldTimeTracking) {
        this.worldTimeTracking = worldTimeTracking;
    }
    @Condition
    public boolean execute(@Fact("player") Player p,
                           @Fact("conditionCompare") ConditionCompare conditionCompare,
                           @Fact("numericValue") double numericValue) {
        int secondsInWorld = worldTimeTracking.getSecondsInWorldForPlayer(p);

        return ConditionHelpers.calculateConditionCompare(conditionCompare, secondsInWorld, numericValue);
    }

    @Action
    public void action() {}
}
