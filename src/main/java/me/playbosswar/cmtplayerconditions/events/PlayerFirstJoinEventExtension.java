package me.playbosswar.cmtplayerconditions.events;

import me.playbosswar.com.CommandTimerPlugin;
import me.playbosswar.com.api.ConditionExtension;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.com.api.events.EventExtension;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerFirstJoinEventExtension extends EventExtension implements Listener {
    private final ConditionExtension extension;

    public PlayerFirstJoinEventExtension(ConditionExtension extension) {
        this.extension = extension;
    }

    @Override
    public @NotNull String getEventName() {
        return "PLAYER_FIRST_JOIN";
    }

    @Override
    public @NotNull String[] getEventDescription() {
        return new String[]{"Triggered when a player joins the server for the first time"};
    }

    @Override
    public ArrayList<NeededValue<?>> getReturnedValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("INITIAL_BIOME", "Initial biome on join", String.class, ""));

        return values;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPlayedBefore()) {
            return;
        }

        Location loc = e.getPlayer().getLocation();
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("INITIAL_BIOME", "Initial biome on join", String.class, loc.getWorld().getBiome(loc).toString()));
        CommandTimerPlugin.getInstance().getEventsManager().handleTriggeredEvent(extension, this, values);
    }
}
