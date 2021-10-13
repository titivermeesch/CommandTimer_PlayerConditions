package me.playbosswar.cmtplayerconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

import java.util.ArrayList;

public class PlayerOpCondition implements ConditionRule {
    @Override
    public String getName() {
        return "IS_OP";
    }

    @Override
    public String getDescription() {
        return "Check if user is OP";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");

        if(p == null) {
            return true;
        }

        return p.isOp();
    }

    public void execute(Facts facts) {}

    public int compareTo(Rule o) { return 0; }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        return null;
    }
}
