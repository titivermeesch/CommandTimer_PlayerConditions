package me.playbosswar.cmtplayerconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.com.conditionsengine.ConditionCompare;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerIsInWorldCondition implements ConditionRule {
    @Override
    public String getName() {
        return "IS_IN_WORLD";
    }

    @Override
    public String getDescription() {
        return "Check if player is in correct world";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");
        String worldName = facts.get("worldName");

        if(p == null) {
            return true;
        }

        return p.getWorld().getName().equals(worldName);
    }

    @Override
    public void execute(Facts facts) { }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();

        values.add(new NeededValue<>(
                "worldName",
                "World Name",
                String.class,
                "world"));

        return values;
    }

    @Override
    public int compareTo(@NotNull Rule o) {
        return 0;
    }
}
