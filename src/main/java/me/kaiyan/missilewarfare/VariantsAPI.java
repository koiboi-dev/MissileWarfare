package me.kaiyan.missilewarfare;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import me.kaiyan.missilewarfare.Items.MissileClass;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class VariantsAPI {
    public static String getStrVariantFromInt(int type){
        switch (type) {
            case 0:
                return "UNKNOWN";
            case 1:
                return "SMR";
            case 2:
                return "SMHE";
            case 3:
                return "SMLR";
            case 4:
                return "SMAC";
        }
        return "NONE";
    }

    public static int getIntTypeFromSlimefunitem(SlimefunItem item){
        switch (item.getId()) {
            case "SMALLMISSILE":
                return 1;
            case "SMALLMISSILEHE":
                return 2;
            case "SMALLMISSILELR":
                return 3;
            case "SMALLMISSILEAC":
                return 4;
        }
        return 0;
    }

    public static SlimefunItem getFirstMissile(Inventory inv){
        for (ItemStack item : inv){
            if (item != null){
                SlimefunItem item1 = SlimefunItem.getByItem(item);
                ItemUtils.consumeItem(item, false);
                return item1;
            }
        }
        return null;
    }

    public static MissileClass missileStatsFromType(int type){
        switch (type){
            case 1:
                return new MissileClass(2,1500, 2, 100, 1);
            case 2:
                return new MissileClass(1,1000, 2.5, 130, 2);
            case 3:
                return new MissileClass(2,2750,2, 100, 3);
            case 4:
                return new MissileClass(2, 1750,2, 30, 4);
        }
        return null;
    }

    public static boolean isInRange(int dist, int type){
        MissileClass missile = missileStatsFromType(type);
        return dist <= missile.range;

    }

    public static void spawnMissileTrail(World world, int type, Vector pos, Vector velocity){
        if (type == 1 || type == 2 || type == 3 || type == 4) {
            world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, 0, 0, 0, 0.1, null, true);
            if (type == 1){
                world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, 0, 0, 0, 0.1, null, true);
            } else if (type == 2){
                world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, -velocity.getX()+((Math.random()-0.5)*0.25), -velocity.getY()+((Math.random()-0.5)*0.25), -velocity.getZ()+((Math.random()-0.5)*0.25), 0.25, null, true);
                world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, -velocity.getX()+((Math.random()-0.5)*0.25), -velocity.getY()+((Math.random()-0.5)*0.25), -velocity.getZ()+((Math.random()-0.5)*0.25), 0.25, null, true);
            } else if (type == 3){
                world.spawnParticle(Particle.END_ROD, pos.toLocation(world), 0, -velocity.getX()+((Math.random()-0.5)*0.25), -velocity.getY()+((Math.random()-0.5)*0.25), -velocity.getZ()+((Math.random()-0.5)*0.25), 0.3, null, true);
            } else if (type == 4){
                world.spawnParticle(Particle.CRIT, pos.toLocation(world), 0, -velocity.getX()+((Math.random()-0.5)*0.25), -velocity.getY()+((Math.random()-0.5)*0.25), -velocity.getZ()+((Math.random()-0.5)*0.25), 0.3, null, true);
            }
        }
    }
}
