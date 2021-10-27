package me.playbosswar.cmtplayerconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerHasRainCondition implements ConditionRule {
    @Override
    public String getName() {
        return "HAS_RAIN";
    }

    @Override
    public String getDescription() {
        return "Check if player is currently in rain";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");

        if(p == null) {
            return true;
        }

        return p.getWorld().getClearWeatherDuration() == 0;
    }

    @Override
    public void execute(Facts facts) {}

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        return null;
    }

    @Override
    public int compareTo(@NotNull Rule o) {
        return 0;
    }
}
