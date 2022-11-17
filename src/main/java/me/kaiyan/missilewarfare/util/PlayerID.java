package me.kaiyan.missilewarfare.util;

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public final class PlayerID {
    private static final HashMap<String, Collection<OfflinePlayer>> players = new HashMap<>();
    public static List<Player> targets = new ArrayList<>();

    private PlayerID() {
        throw new RuntimeException("Do not run this constructor");
    }

    public static Map<String, Collection<OfflinePlayer>> getPlayers() {
        return players;
    }

    public static void loadPlayers(@NotNull Config file) {
        Set<String> keys = file.getKeys("id.");
        Map<String, List<OfflinePlayer>> playerMaps = keys
                .stream()
                .collect(Collectors.toMap(key -> key, key -> file
                        .getStringList("id." + key + ".players")
                        .stream()
                        .map(uuidString -> {
                            try {
                                UUID uuid = UUID.fromString(uuidString.trim());
                                return Bukkit.getOfflinePlayer(uuid);
                            } catch (NumberFormatException e) {
                                //cannot read UUID
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())));
        players.putAll(playerMaps);
    }

    public static void savePlayers(@NotNull Config file) {
        players.forEach((key, value) -> {
            List<String> ids = value.stream().map(p -> p.getUniqueId().toString()).collect(Collectors.toList());
            file.setValue("id." + key + ".players", ids);
        });
        file.save();
    }
}
