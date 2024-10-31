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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class PlayerFirstJoinOfDayEventExtension extends EventExtension implements Listener {
    private final ConditionExtension extension;

    public PlayerFirstJoinOfDayEventExtension(ConditionExtension extension) {
        this.extension = extension;
    }

    @Override
    public @NotNull String getEventName() {
        return "PLAYER_FIRST_JOIN_OF_DAY";
    }

    @Override
    public @NotNull String[] getEventDescription() {
        return new String[]{"Triggered when a player joins the server for the first time of the day"};
    }

    @Override
    public ArrayList<NeededValue<?>> getReturnedValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("INITIAL_BIOME", "Initial biome on join", String.class, ""));

        return values;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        long lastPlayed = e.getPlayer().getLastPlayed();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT);
        long startOfDayEpoch = startOfDay.toEpochSecond(ZoneOffset.UTC) * 1000;

        System.out.println(e.getPlayer().getLastPlayed());
        System.out.println(startOfDayEpoch);

        if (lastPlayed >= startOfDayEpoch && lastPlayed != 0) {
            return;
        }

        Location loc = e.getPlayer().getLocation();
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("INITIAL_BIOME", "Initial biome on join", String.class, loc.getWorld().getBiome(loc).toString()));
        CommandTimerPlugin.getInstance().getEventsManager().handleTriggeredEvent(extension, this, values);
    }
}
