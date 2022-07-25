package me.kaiyan.missilewarfare.util;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.kaiyan.missilewarfare.items.MissileClass;
import me.kaiyan.missilewarfare.missiles.MissileConfig;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

public class VariantsAPI {
    // the amount of magic values in this file is astounding. -ck

    public static Random rand = new Random();

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
                return "MR";
            case 7:
                return "MHE";
            case 8:
                return "MLR";
            case 9:
                return "MAC";
            case 10:
                return "MAPT1";
            case 11:
                return "MAPT2";
            case 12:
                return "MAPT3";
            case 13:
                return "MGAS";
            case 14:
                return "EXCV";
            case 15:
                return "STCK";
            case 16:
                return "ICBM";
            case 17:
                return "CLST";
            case 18:
                return "NPLM";
            case 19:
                return "AVMS";
            case 20:
                return "AVHE";
            case 21:
                return "AVLR";
            case 22:
                return "AVAC";
            case 23:
                return "ELYT";
        }
        return "NONE";
    }

    public static int getIntTypeFromSlimefunitem(SlimefunItem item){
        return getIntTypeFromSlimefunitemID(item.getId());
    }

    public static int getIntTypeFromSlimefunitemID(String id){
        switch (id) {
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
            case "MISSILEAPONE":
                return 10;
            case "MISSILEAPTWO":
                return 11;
            case "MISSILEAPTHR":
                return 12;
            case "MISSILEGAS":
                return 13;
            case "MISSILEEXCAV":
                return 14;
            case "MISSILESTICK":
                return 15;
            case "MISSILEICBM":
                return 16;
            case "MISSILECLUSTER":
                return 17;
            case "MISSILENAPALM":
                return 18;
            case "MISSILEADV":
                return 19;
            case "MISSILEHEADV":
                return 20;
            case "MISSILELRADV":
                return 21;
            case "MISSILEACADV":
                return 22;
            case "ANTIELYTRAMISSILE":
                return 23;
        }
        return 0;
    }

    public static ItemStack getFirstMissile(Inventory inv){
        // Returns the first missile item in an inventory
        for (ItemStack item : inv){
            SlimefunItem slimefun_item = SlimefunItem.getByItem(item);
            if (slimefun_item != null && getIntTypeFromSlimefunitem(slimefun_item) != 0){
                return item;
            }
        }
        return null;
    }

    public static ItemStack getOtherFirstMissile(Inventory inv, SlimefunItem slimefunItem){
        // Returns the first missile matching the type ID of second argument.
        for (ItemStack item : inv){
            SlimefunItem _item = SlimefunItem.getByItem(item);
            if (_item != null && _item.getId().equals(slimefunItem.getId())){
                return item;
            }
        }
        return null;
    }

    public static MissileClass missileStatsFromType(int type){
        switch (type){
            case 1:
                return MissileConfig.missiles[0];
            case 2:
                return MissileConfig.missiles[1];
            case 3:
                return MissileConfig.missiles[2];
            case 4:
                return MissileConfig.missiles[3];
            case 5:
                return MissileConfig.missiles[4];
            case 6:
                return MissileConfig.missiles[5];
            case 7:
                return MissileConfig.missiles[6];
            case 8:
                return MissileConfig.missiles[7];
            case 9:
                return MissileConfig.missiles[8];
            case 10:
                return MissileConfig.missiles[9];
            case 11:
                return MissileConfig.missiles[10];
            case 12:
                return MissileConfig.missiles[11];
            case 13:
                return MissileConfig.missiles[12];
            case 14:
                return MissileConfig.missiles[13];
            case 15:
                return MissileConfig.missiles[14];
            case 16:
                return MissileConfig.missiles[15];
            case 17:
                return MissileConfig.missiles[16];
            case 18:
                return MissileConfig.missiles[17];
            case 19:
                return MissileConfig.missiles[18];
            case 20:
                return MissileConfig.missiles[19];
            case 21:
                return MissileConfig.missiles[20];
            case 22:
                return MissileConfig.missiles[21];
            case 23:
                return MissileConfig.missiles[22];
        }
        return null;
    }

    public static boolean isInRange(int dist, int type){
        MissileClass missile = missileStatsFromType(type);
        return dist >= missile.range;
    }

    public static void spawnMissileTrail(World world, int type, Vector pos, Vector velocity){
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
        if (type == 6 || type == 7 || type == 8 || type == 9 || type == 10){
            // 'Missile' TYPES
            world.spawnParticle(Particle.VILLAGER_HAPPY, pos.toLocation(world), 1);
        }
        if (type == 10 || type == 11 || type == 12){
            world.spawnParticle(Particle.DRAGON_BREATH, pos.toLocation(world), 1);
        }
    }
}
