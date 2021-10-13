package me.playbosswar.cmtplayerconditions.conditions;

import me.playbosswar.cmtplayerconditions.utils.WorldTimeTracking;
import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.com.conditionsengine.ConditionCompare;
import me.playbosswar.com.conditionsengine.conditions.ConditionHelpers;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

import java.util.ArrayList;

public class PlayerTimeInWorldCondition implements ConditionRule {
    private final WorldTimeTracking worldTimeTracking;

    public PlayerTimeInWorldCondition(WorldTimeTracking worldTimeTracking) {
        this.worldTimeTracking = worldTimeTracking;
    }

    @Override
    public String getName() {
        return "TIME_IN_WORLD";
    }

    @Override
    public String getDescription() {
        return "Check for how long the player is in the same world";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");
        ConditionCompare conditionCompare = facts.get("conditionCompare");
        double numericValue = facts.get("numericValue");

        if(p == null) {
            return true;
        }

        int secondsInWorld = worldTimeTracking.getSecondsInWorldForPlayer(p);

        return ConditionHelpers.calculateConditionCompare(conditionCompare, secondsInWorld, numericValue);
    }

    @Override
    public void execute(Facts facts) {}

    public int compareTo(Rule o) { return 0; }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();

        values.add(new NeededValue<>(
                "conditionCompare",
                "Compare Rule",
                ConditionCompare.class,
                ConditionCompare.EQUAL));
        values.add(new NeededValue<>(
                "numericValue",
                "Time in seconds",
                Double.class,
                0.0));

        return values;
    }
}
