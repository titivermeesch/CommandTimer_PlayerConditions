package me.playbosswar.cmtplayerconditions.events;

import me.playbosswar.com.CommandTimerPlugin;
import me.playbosswar.com.api.ConditionExtension;
import me.playbosswar.com.api.NeededValue;
import me.playbosswar.com.api.events.EventExtension;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerBiomeChangeEvent extends EventExtension implements Listener {
    private final ConditionExtension extension;

    public PlayerBiomeChangeEvent(ConditionExtension extension) {
        this.extension = extension;
    }

    @Override
    public @NotNull String getEventName() {
        return "BIOME_CHANGE";
    }

    @Override
    public @NotNull String[] getEventDescription() {
        return new String[] { "Triggered when a player enters a different biome" };
    }

    @Override
    public ArrayList<NeededValue<?>> getReturnedValues() {
        ArrayList<NeededValue<?>> values = new ArrayList<>();
        values.add(new NeededValue<>("BIOME_NAME", "Biome name", String.class, ""));

        return values;
    }

    @EventHandler
    public void onBiomeChange(PlayerMoveEvent e) {
        handleMoveEvent(e);
    }

    @EventHandler
    public void onTeleportToDifferentBiome(PlayerTeleportEvent e) {
        handleMoveEvent(e);
    }

    private void handleMoveEvent(PlayerMoveEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();
        String fromBiome = from.getWorld().getBiome(from.getBlockX(), from.getBlockY()).toString();
        String toBiome = to.getWorld().getBiome(to.getBlockX(), to.getBlockY()).toString();

        if (!fromBiome.equals(toBiome)) {
            ArrayList<NeededValue<?>> values = new ArrayList<>();
            values.add(new NeededValue<>("BIOME_NAME", "Biome name", String.class, toBiome));
            CommandTimerPlugin.getInstance().getEventsManager().handleTriggeredEvent(extension, this, values);
        }
    }
}
