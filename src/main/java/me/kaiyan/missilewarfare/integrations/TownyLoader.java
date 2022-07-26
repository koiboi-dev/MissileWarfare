package me.kaiyan.missilewarfare.integrations;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Town;
import me.kaiyan.missilewarfare.MissileWarfare;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TownyLoader {
    public static void setup(){
        MissileWarfare.townyEnabled = true;
    }

    public static boolean exploded(Player nearestPlayer, Location loc){
        try {
            Town homeTown = TownyAPI.getInstance().getResident(nearestPlayer).getTown();
            Town targetTown = TownyAPI.getInstance().getTown(loc);
            if (targetTown == null){
                return false;
            } else {
                if (homeTown.hasEnemy(targetTown)) {
                    return true;
                }
            }
        } catch (NotRegisteredException ignored) {
            return false;
        }
        return false;
    }
}
