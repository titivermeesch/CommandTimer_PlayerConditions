package me.playbosswar.cmtplayerconditions.utils;

import org.bukkit.Location;

import java.lang.reflect.Method;

public class BiomeUtil {
    private static Method getBiomeMethod;
    private static boolean useNewAPI;
    private static boolean initialized = false;

    static {
        detectBiomeAPI();
    }

    private static void detectBiomeAPI() {
        try {
            // Try new API first (1.15+): getBiome(int x, int y, int z)
            getBiomeMethod = org.bukkit.World.class.getMethod("getBiome", int.class, int.class, int.class);
            useNewAPI = true;
        } catch (NoSuchMethodException e) {
            try {
                // Fall back to old API (1.8-1.14): getBiome(int x, int z)
                getBiomeMethod = org.bukkit.World.class.getMethod("getBiome", int.class, int.class);
                useNewAPI = false;
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException("Unable to find getBiome method", ex);
            }
        }
        initialized = true;
    }

    /**
     * Gets the biome name at the specified location.
     * Works with both old (1.8-1.14) and new (1.15+) Spigot APIs.
     *
     * @param location The location to get the biome from
     * @return The biome name as a string
     * @throws RuntimeException if unable to get the biome
     */
    public static String getBiomeName(Location location) {
        if (!initialized) {
            detectBiomeAPI();
        }

        try {
            Object biome;
            if (useNewAPI) {
                biome = getBiomeMethod.invoke(
                    location.getWorld(),
                    location.getBlockX(),
                    location.getBlockY(),
                    location.getBlockZ()
                );
            } else {
                biome = getBiomeMethod.invoke(
                    location.getWorld(),
                    location.getBlockX(),
                    location.getBlockZ()
                );
            }
            return biome.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error getting biome at location: " + location, e);
        }
    }
}
