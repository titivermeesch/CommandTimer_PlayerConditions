package me.playbosswar.cmtplayerconditions;

import me.playbosswar.cmtplayerconditions.utils.WorldTimeTracking;
import me.playbosswar.com.api.ConditionExtension;
import me.playbosswar.cmtplayerconditions.conditions.PlayerHealthCondition;
import me.playbosswar.cmtplayerconditions.conditions.PlayerOpCondition;
import me.playbosswar.cmtplayerconditions.conditions.PlayerTimeInWorldCondition;
import org.jeasy.rules.api.Rules;
import org.jetbrains.annotations.NotNull;

public class CommandTimerPlayerConditions extends ConditionExtension {
    @Override
    public @NotNull String getConditionGroupName() {
        return "PLAYERS";
    }

    @Override
    public @NotNull String[] getDescription() {
        return new String[]{ "§7Interact with player data" };
    }

    @Override
    public @NotNull String getAuthor() {
        return "PlayBossWar";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    public @NotNull Rules getRules() {
        Rules rules = new Rules();
        ;
        rules.register(
                new PlayerHealthCondition(),
                new PlayerOpCondition(),
                new PlayerTimeInWorldCondition(new WorldTimeTracking(getCommandTimerPlugin())));

        return rules;
    }
}
