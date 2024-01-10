package me.playbosswar.cmtplayerconditions.conditions;

import me.playbosswar.com.api.ConditionRule;
import me.playbosswar.com.api.NeededValue;
import org.bukkit.entity.Player;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerIsInGamemodeCondition implements ConditionRule {
    @Override
    public String getName() {
        return "IS_IN_GAMEMODE";
    }

    @Override
    public String getDescription() {
        return "Check if player is in specific gamemode";
    }

    @Override
    public boolean evaluate(Facts facts) {
        Player p = facts.get("player");
        String gameMode = facts.get("gameMode");

        if(p == null) {
            return true;
        }

        return p.getGameMode().toString().equalsIgnoreCase(gameMode);
    }

    @Override
    public void execute(Facts facts) { }

    @Override
    public ArrayList<NeededValue<?>> getNeededValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();

        values.add(new NeededValue<>(
                "gameMode",
                "Game Mode",
                String.class,
                "survival"));

        return values;
    }

    @Override
    public int compareTo(@NotNull Rule o) {
        return 0;
    }
}
