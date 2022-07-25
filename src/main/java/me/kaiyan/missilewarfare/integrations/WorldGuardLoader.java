package me.kaiyan.missilewarfare.integrations;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.kaiyan.missilewarfare.MissileWarfare;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class WorldGuardLoader {
    public static StateFlag ALLOW_MISSILE_EXPLODE;

    public static void load(){
        MissileWarfare.worldGuardEnabled = true;
        MissileWarfare.getInstance().getLogger().info("WorldGuard Enabled!");
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            // create a flag with the name "my-custom-flag", defaulting to true
            StateFlag flag = new StateFlag("allow-missile-explode", true);
            registry.register(flag);
            ALLOW_MISSILE_EXPLODE = flag; // only set our field if there was no error
        } catch (FlagConflictException e) {
            // some other plugin registered a flag by the same name already.
            // you can use the existing flag, but this may cause conflicts - be sure to check type
            Flag<?> existing = registry.get("allow-missile-explode");
            if (existing instanceof StateFlag) {
                ALLOW_MISSILE_EXPLODE = (StateFlag) existing;
            } else {
                MissileWarfare.getInstance().getLogger().severe("!! WARNING: WORLDGUARD FLAG ALLOW_MISSILE_EXPLODE HAS BEEN TAKEN BY ANOTHER PLUGIN, WORLDGUARD SUPPORT IS DISABLED !!");
                MissileWarfare.worldGuardEnabled = false;
            }
        }
    }

    public static void explode(World world, Vector pos, double power, Entity armourStand, Player nearestPlayer){
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(new BukkitWorld(world));
        if (regions == null){
            world.createExplosion(pos.toLocation(world), (float) power, false, true, armourStand);
        } else {
            ApplicableRegionSet set = regions.getApplicableRegions(BlockVector3.at(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ()));
            if (WorldGuardPlugin.inst().wrapPlayer(nearestPlayer) != null){
                world.createExplosion(pos.toLocation(world), (float) power, false, true, armourStand);
                return;
            }
            world.createExplosion(pos.toLocation(world), (float) power, false, set.testState(WorldGuardPlugin.inst().wrapPlayer(nearestPlayer), WorldGuardLoader.ALLOW_MISSILE_EXPLODE), armourStand);
        }
    }
}
