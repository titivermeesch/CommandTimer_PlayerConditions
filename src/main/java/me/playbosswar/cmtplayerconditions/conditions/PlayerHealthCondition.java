package me.playbosswar.cmtplayerconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.com.conditionsengine.ConditionCompare;
import me.playbosswar.com.conditionsengine.conditions.ConditionHelpers;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

import java.util.ArrayList;

public class PlayerHealthCondition implements ConditionRule {
    @Override
    public String getName() {
        return "HEALTH";
    }

    @Override
    public String getDescription() {
        return "Check player health";
    }

    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");
        ConditionCompare conditionCompare = facts.get("conditionCompare");
        double numericValue = facts.get("numericValue");

        if(p == null) {
            return true;
        }

        return ConditionHelpers.calculateConditionCompare(conditionCompare, p.getHealth(), numericValue);
    }

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
                "Player Health",
                Double.class,
                0.0));

        return values;
    }
}
