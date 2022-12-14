package me.kaiyan.missilewarfare.integrations;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import me.kaiyan.missilewarfare.MissileWarfare;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TownyLoader {
    public static void setup(){
        MissileWarfare.townyEnabled = true;
        MissileWarfare.getInstance().getLogger().info("Towny Support Enabled!");
    }

    public static boolean exploded(Player nearestPlayer, Location loc){
        try {
            Resident resident = TownyAPI.getInstance().getResident(nearestPlayer);
            Town targetTown = TownyAPI.getInstance().getTown(loc);
            if (resident == null || targetTown == null){
                return false;
            } else {
                if (resident.getTown().hasEnemy(targetTown)) {
                    return true;
                }
            }
        } catch (NotRegisteredException ignored) {
            return false;
        }
        return false;
    }
}
