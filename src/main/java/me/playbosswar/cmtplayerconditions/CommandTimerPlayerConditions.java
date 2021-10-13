package me.playbosswar.cmtplayerconditions;

import me.playbosswar.cmtplayerconditions.conditions.PlayerHasPermissionCondition;
import me.playbosswar.cmtplayerconditions.utils.WorldTimeTracking;
import me.playbosswar.com.api.ConditionExtension;
import me.playbosswar.cmtplayerconditions.conditions.PlayerHealthCondition;
import me.playbosswar.cmtplayerconditions.conditions.PlayerOpCondition;
import me.playbosswar.cmtplayerconditions.conditions.PlayerTimeInWorldCondition;
import me.playbosswar.com.api.ConditionRules;
import org.jetbrains.annotations.NotNull;

public class CommandTimerPlayerConditions extends ConditionExtension {
    ConditionRules rules = new ConditionRules();

    public CommandTimerPlayerConditions() {
        rules.register(
                new PlayerHealthCondition(),
                new PlayerOpCondition(),
                new PlayerTimeInWorldCondition(new WorldTimeTracking(getCommandTimerPlugin())),
                new PlayerHasPermissionCondition());
    }

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

    public @NotNull ConditionRules getRules() {
        return rules;
    }
}
