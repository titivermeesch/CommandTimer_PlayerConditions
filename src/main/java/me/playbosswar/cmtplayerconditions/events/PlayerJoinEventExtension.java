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

public class PlayerJoinEventExtension extends EventExtension implements Listener {
    private final ConditionExtension extension;

    public PlayerJoinEventExtension(ConditionExtension extension) {
        this.extension = extension;
    }

    @Override
    public @NotNull String getEventName() {
        return "PLAYER_JOINED";
    }

    @Override
    public @NotNull String[] getEventDescription() {
        return new String[] { "Triggered when a player joins the server" };
    }

    @Override
    public ArrayList<NeededValue<?>> getReturnedValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("INITIAL_BIOME", "Initial biome on join", String.class, ""));

        return values;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Location loc = e.getPlayer().getLocation();
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("INITIAL_BIOME", "Initial biome on join", String.class,
                loc.getWorld().getBiome(loc.getBlockX(), loc.getBlockY()).toString()));
        CommandTimerPlugin.getInstance().getEventsManager().handleTriggeredEvent(extension, this, values);
    }
}
