package me.kaiyan.missilewarfare;

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerID {
    public static HashMap<String, List<OfflinePlayer>> players = new HashMap<>();
    public static List<Player> targets = new ArrayList<>();

    public static void loadPlayers(Config file){
        Set<String> keys = file.getKeys("id.");
        for (String key : keys){
            List<OfflinePlayer> _players = new ArrayList<>();
            List<String> strs = file.getStringList("id."+key+".players");
            for (String uuidstr : strs){
                uuidstr = uuidstr.trim();
                _players.add(Bukkit.getOfflinePlayer(UUID.fromString(uuidstr)));
            }
            players.put(key, _players);
        }
    }

    public static void savePlayers(Config file){
        for (Map.Entry<String, List<OfflinePlayer>> entry : players.entrySet()){
            List<String> uuids = new ArrayList<>();
            for (OfflinePlayer player : entry.getValue()){
                uuids.add(player.getUniqueId().toString());
            }
            file.setValue("id."+entry.getKey()+".players", uuids);
        }
        file.save();
    }
}
