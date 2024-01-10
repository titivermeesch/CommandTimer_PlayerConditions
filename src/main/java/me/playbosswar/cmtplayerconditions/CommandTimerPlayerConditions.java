package me.playbosswar.cmtplayerconditions;

import me.playbosswar.cmtplayerconditions.conditions.*;
import me.playbosswar.cmtplayerconditions.events.PlayerBiomeChangeEvent;
import me.playbosswar.cmtplayerconditions.events.PlayerJoinEventExtension;
import me.playbosswar.cmtplayerconditions.utils.WorldTimeTracking;
import me.playbosswar.com.api.ConditionExtension;
import me.playbosswar.com.api.ConditionRules;
import me.playbosswar.com.api.events.EventExtension;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandTimerPlayerConditions extends ConditionExtension {
    ConditionRules rules = new ConditionRules();

    public CommandTimerPlayerConditions() {
        rules.register(
                new PlayerHealthCondition(),
                new PlayerOpCondition(),
                new PlayerTimeInWorldCondition(new WorldTimeTracking(getCommandTimerPlugin())),
                new PlayerHasPermissionCondition(),
                new PlayerHasRainCondition(),
                new PlayerHasStormCondition(),
                new PlayerIsInWorldCondition(),
                new PlayerIsInGamemodeCondition(),
                new PlayerIsFlyingCondition());
    }

    @Override
    public @NotNull String getConditionGroupName() {
        return "PLAYERS";
    }

    @Override
    public @NotNull String[] getDescription() {
        return new String[]{"§7Interact with player data"};
    }

    @Override
    public @NotNull String getAuthor() {
        return "PlayBossWar";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.3.0";
    }

    public @NotNull ConditionRules getRules() {
        return rules;
    }

    @Override
    public ArrayList<EventExtension> getEvents() {
        ArrayList<EventExtension> events = new ArrayList<>();
        events.add(new PlayerBiomeChangeEvent(this));
        events.add(new PlayerJoinEventExtension(this));
        return events;
    }
}
