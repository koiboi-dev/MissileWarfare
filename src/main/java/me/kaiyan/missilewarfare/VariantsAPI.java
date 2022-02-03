package me.kaiyan.missilewarfare;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.kaiyan.missilewarfare.Items.MissileClass;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

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
            case 5:
                return "GAAM";
            case 6:
                return "NMR";
            case 7:
                return "NMHE";
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
            case "ANTIAIRMISSILE":
                return 5;
            case "MISSILE":
                return 6;
            case "MISSILEHE":
                return 7;
            case "MISSILELR":
                return 8;
            case "MISSILEAC":
                return 9;
        }
        return 0;
    }

    public static ItemStack getFirstMissile(Inventory inv){
        for (ItemStack item : inv){
            SlimefunItem _item = SlimefunItem.getByItem(item);
            if (_item != null && getIntTypeFromSlimefunitem(_item) != 0){
                return item;
            }
        }
        return null;
    }

    public static MissileClass missileStatsFromType(int type){
        switch (type){
            case 1:
                return new MissileClass(2,500, 2, 100, 1);
            case 2:
                return new MissileClass(1,400, 2.5, 120, 2);
            case 3:
                return new MissileClass(2,600,2, 100, 3);
            case 4:
                return new MissileClass(2, 550,2, 60, 4);
            case 5:
                return new MissileClass(3, 400,3, 0, 5);
            case 6:
                return new MissileClass(2.5f, 650, 3.5, 80, 6);
            case 7:
                return new MissileClass(2, 600, 4, 100, 7);
            case 8:
                return new MissileClass(2.5f, 800, 3.5, 80, 8);
            case 9:
                return new MissileClass(2.5f, 600, 3.5,50, 9);
        }
        return null;
    }

    public static boolean isInRange(int dist, int type){
        MissileClass missile = missileStatsFromType(type);
        return dist <= missile.range;

    }

    public static void spawnMissileTrail(World world, int type, Vector pos, Vector velocity){
        Random rand = new Random();
        world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, 0, 0, 0, 0.1, null, true);
        world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, -velocity.getX()+((rand.nextDouble()-0.5)*0.5), -velocity.getY()+((rand.nextDouble()-0.5)*0.5), -velocity.getZ()+((rand.nextDouble()-0.5)*0.5), 0.25, null, true);
        world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, -velocity.getX()+((rand.nextDouble()-0.5)*0.5), -velocity.getY()+((rand.nextDouble()-0.5)*0.5), -velocity.getZ()+((rand.nextDouble()-0.5)*0.5), 0.25, null, true);
        world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, -velocity.getX()+((rand.nextDouble()-0.5)*0.5), -velocity.getY()+((rand.nextDouble()-0.5)*0.5), -velocity.getZ()+((rand.nextDouble()-0.5)*0.5), 0.25, null, true);
        if (type == 2 || type == 7){
            //HE MISSILES
            world.spawnParticle(Particle.VILLAGER_ANGRY, pos.toLocation(world), 0, 0,0,0, 0.1, null, true);
        } else if (type == 3 || type == 8){
            //LONG RANGE
            world.spawnParticle(Particle.END_ROD, pos.toLocation(world), 0, -velocity.getX()+((rand.nextDouble()-0.5)*0.25), -velocity.getY()+((rand.nextDouble()-0.5)*0.25), -velocity.getZ()+((rand.nextDouble()-0.5)*0.25), 0.3, null, true);
        } else if (type == 4 || type == 9){
            //ACCURATE MISSILES
            world.spawnParticle(Particle.CRIT, pos.toLocation(world), 0, -velocity.getX()+((rand.nextDouble()-0.5)*0.25), -velocity.getY()+((rand.nextDouble()-0.5)*0.25), -velocity.getZ()+((rand.nextDouble()-0.5)*0.25), 0.3, null, true);
        }
        if (type == 6 || type == 7 || type == 8 || type == 9){
            // 'Missile' TYPES
            world.spawnParticle(Particle.VILLAGER_HAPPY, pos.toLocation(world), 1);
        }
    }
}
