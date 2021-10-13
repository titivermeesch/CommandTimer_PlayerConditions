package me.playbosswar.cmtplayerconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerHasPermissionCondition implements ConditionRule {
    @Override
    public String getName() {
        return "HAS_PERMISSION";
    }

    @Override
    public String getDescription() {
        return "Check if player has the correct permission";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");
        String permission = facts.get("permission");

        if(p == null) {
            return true;
        }

        return p.hasPermission(permission);
    }

    @Override
    public void execute(Facts facts) { }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();

        values.add(new NeededValue<>(
                "permission",
                "Permission",
                String.class,
                ""));

        return values;
    }

    @Override
    public int compareTo(@NotNull Rule o) {
        return 0;
    }
}
