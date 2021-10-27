package me.playbosswar.cmtplayerconditions.conditions;

import me.playbosswar.com.api.ConditionExtension;
import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerHasStormCondition implements ConditionRule {
    @Override
    public String getName() {
        return "HAS_STORM";
    }

    @Override
    public String getDescription() {
        return "Check if player is currently in a storm";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");

        if(p == null) {
            return true;
        }

        return p.getWorld().hasStorm();
    }

    @Override
    public void execute(Facts facts) { }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        return null;
    }


    @Override
    public int compareTo(@NotNull Rule o) {
        return 0;
    }
}
