package me.playbosswar.cmtplayerconditions.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import me.playbosswar.com.CommandTimerPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class WorldTimeTracking implements Listener {
    private BukkitTask task;
    private final Map<Player, Integer> secondsInWorld = new HashMap<>();

    public WorldTimeTracking(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        task = CommandTimerPlugin.getScheduler().runTaskTimer(() -> {
            secondsInWorld.forEach((player, value) -> secondsInWorld.put(player, value + 1));
        }, 10L, 20L);
    }

    public void cancel() {
        if (task != null) {
            task.cancel();
        }
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        secondsInWorld.put(e.getPlayer(), 0);
    }

    @EventHandler
    private void onLeave(PlayerQuitEvent e) {
        secondsInWorld.remove(e.getPlayer());
    }

    @EventHandler
    private void onWorldChange(PlayerTeleportEvent e) {
        if (e.getFrom().getWorld().equals(e.getTo().getWorld())) {
            return;
        }

        secondsInWorld.replace(e.getPlayer(), 0);
    }

    public Map<Player, Integer> getSecondsInWorld() {
        return secondsInWorld;
    }

    public int getSecondsInWorldForPlayer(Player p) {
        if (!secondsInWorld.containsKey(p)) {
            return 0;
        }

        return secondsInWorld.get(p);
    }
}
