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

    public static ItemStack getOtherFirstMissile(Inventory inv, SlimefunItem slimefunItem){
        for (ItemStack item : inv){
            SlimefunItem _item = SlimefunItem.getByItem(item);
            if (_item != null && _item == slimefunItem){
                return item;
            }
        }
        return null;
    }

    public static MissileClass missileStatsFromType(int type){
        switch (type){
            case 1:
                return new MissileClass(2,500, 3, 100, 1);
            case 2:
                return new MissileClass(1,400, 3.5, 120, 2);
            case 3:
                return new MissileClass(2,600,3, 100, 3);
            case 4:
                return new MissileClass(2, 550,3, 60, 4);
            case 5:
                return new MissileClass(3, 400,4, 0, 5);
            case 6:
                return new MissileClass(2.5f, 650, 4.5, 80, 6);
            case 7:
                return new MissileClass(2, 600, 5, 100, 7);
            case 8:
                return new MissileClass(2.5f, 800, 4.5, 80, 8);
            case 9:
                return new MissileClass(2.5f, 600, 4.5,50, 9);
            case 10:
                return new MissileClass(2, 550, 3.5, 40, 10);
            case 11:
                return new MissileClass(2.5, 700, 4, 30, 11);
            case 12:
                return new MissileClass(3, 850, 4.5, 20, 12);
            case 13:
                return new MissileClass(3, 600, 0.5, 80, 13);
            case 14:
                return new MissileClass(0.5, 100, 8, 50, 14);
            case 15:
                return new MissileClass(4, 600, 1, 70, 15);
            case 16:
                return new MissileClass(5, 3500, 8, 50, 16);
            case 17:
                return new MissileClass(3.5, 600,4, 80, 17);
            case 18:
                return new MissileClass(3, 600, 2, 140, 18);
            case 19:
                return new MissileClass(4, 1200, 4, 40, 19);
            case 20:
                return new MissileClass(3.5, 1200, 5, 35, 20);
            case 21:
                return new MissileClass(4.5, 1750, 4, 40, 21);
            case 22:
                return new MissileClass(4, 1200, 4, 20, 22);
        }
        return null;
    }

    public static boolean isInRange(int dist, int type){
        MissileClass missile = missileStatsFromType(type);
        return dist >= missile.range;
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
        if (type == 6 || type == 7 || type == 8 || type == 9 || type == 10){
            // 'Missile' TYPES
            world.spawnParticle(Particle.VILLAGER_HAPPY, pos.toLocation(world), 1);
        }
        if (type == 10 || type == 11 || type == 12){
            world.spawnParticle(Particle.DRAGON_BREATH, pos.toLocation(world), 1);
        }
    }
}
